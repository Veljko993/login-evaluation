package eco.login.evaluation.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eco.login.evaluation.common.Operation;
import eco.login.evaluation.dao.entity.VehicleTelemetry;
import eco.login.evaluation.dao.repository.VehicleDataDao;
import eco.login.evaluation.dao.repository.VehicleTelemetryDao;
import eco.login.evaluation.exception.UnknownOperationException;
import eco.login.evaluation.exception.ValidationException;
import eco.login.evaluation.model.Filter;
import eco.login.evaluation.model.PropertyFilter;
import eco.login.evaluation.model.VehicleData;
import eco.login.evaluation.service.DataReadingService;
import eco.login.evaluation.service.PropertyDefinitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class DataReadingServiceImpl implements DataReadingService {

    private final PropertyDefinitionService definitionService;
    private final VehicleDataDao vehicleDataDao;
    private final VehicleTelemetryDao vehicleTelemetry;

    public DataReadingServiceImpl(PropertyDefinitionService definitionService, VehicleDataDao vehicleDataDao, VehicleTelemetryDao vehicleTelemetry) {
        this.definitionService = definitionService;
        this.vehicleDataDao = vehicleDataDao;
        this.vehicleTelemetry = vehicleTelemetry;
    }

    @Override
    public String getVehicleData(List<Filter> validFilters) {
        String query = convertToQuery(validFilters);
        List<?> vehicleTelemetryKeys = vehicleDataDao.executeNativeQuery(query);
        Iterable<VehicleTelemetry> vehicleTelemetries = vehicleTelemetry.findAllById((Iterable<Long>) vehicleTelemetryKeys);
        List<VehicleData> vehicleData = new ArrayList<>();
        for (VehicleTelemetry telemetry : vehicleTelemetries) {
            vehicleData.add(VehicleData.convert(telemetry));
        }
        ObjectMapper mapper = new ObjectMapper();
        String value;
        try {
            value = mapper.writeValueAsString(vehicleData);
        } catch (JsonProcessingException e) {
            value = "ERROR: " + e;
        }
        return value;
    }

    private String convertToQuery(List<Filter> validFilters) {
        Collection<PropertyFilter> filters = new HashSet<>();
        try {
            filters = parseFilters(validFilters);
        } catch (ValidationException e) {
            log.error(e.getMessage(), e);
        }
        int counter = 0;
        StringBuilder builder = new StringBuilder("SELECT VHCL_TLMTRY_KY FROM VHCL_TLMTRY VT ");
        for (PropertyFilter filter : filters) {
            String identifier = "TP" + counter++;
            builder.append(" JOIN TLMTRY_PROP ").append(identifier)
                    .append(" ON VT.VHCL_TLMTRY_KY = ").append(identifier).append(".TLMTRY_KY AND ")
                    .append(identifier).append(".TLMTRY_PROP_DEFN_KY = ").append(filter.getPropertyKy()).append(" AND ")
                    .append(identifier).append(propertyFilter(filter));
        }
        return builder.toString();
    }

    private String propertyFilter(PropertyFilter filter) {
        String field = "";
        String value = filter.getFilterValue();
        switch (filter.getPropertyType()) {
            case TEXT -> {
                field = ".TLMTRY_PROP_VALU_TX";
                value = "'" + filter.getFilterValue() + "'";
            }
            case DATE -> {
                field = ".TLMTRY_PROP_VALU_TS";
            }
            case INT -> {
                field = ".TLMTRY_PROP_VALU_INTG";
            }
            case BOOLEAN -> {
                field = ".TLMTRY_PROP_VALU_FG";
            }
            case DOUBLE -> {
                field = ".TLMTRY_PROP_VALU_DCML";
            }
        }
        String queryPart = "";
        switch (filter.getOperation()) {
            case EQUALS -> queryPart = field + "=" + value;
            case LESS_THAN -> queryPart = field + "<" + value;
            case GREATER_THAN -> queryPart = field + ">" + value;
            case CONTAINS -> queryPart = field + "like '%" + filter.getFilterValue() + "%'";
        }
        return queryPart;
    }

    private Collection<PropertyFilter> parseFilters(List<Filter> validFilters) throws ValidationException {
        Map<Integer, PropertyFilter> field = new HashMap<>();
        for (Filter filter : validFilters) {
            PropertyFilter property = definitionService.getProperty(filter.getField());
            try {
                property.setOperation(Operation.parse(filter.getOperation()));
            } catch (UnknownOperationException e) {
                throw new ValidationException("Validation error: Operation invalid. Value: " + filter.getOperation());
            }
            property.setFilterValue(filter.getValue());
            field.put(property.getPropertyKy(), property);
        }
        return field.values();
    }
}
