package eco.login.evaluation.service.impl;

import eco.login.evaluation.common.TelemetryPropertyType;
import eco.login.evaluation.dao.entity.TelemetryProperty;
import eco.login.evaluation.dao.entity.TelemetryPropertyDefinition;
import eco.login.evaluation.dao.entity.VehicleTelemetry;
import eco.login.evaluation.dao.repository.TelemetryPropertyDefinitionDao;
import eco.login.evaluation.exception.ValidationException;
import eco.login.evaluation.model.PropertyFilter;
import eco.login.evaluation.service.PropertyDefinitionService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static eco.login.evaluation.common.TelemetryPropertyType.valueOf;

@Service
@Slf4j
public class PropertyDefinitionServiceImpl implements PropertyDefinitionService {
    private final TelemetryPropertyDefinitionDao propertyDefinitionDao;
    private final Map<String, TelemetryPropertyDefinition> propertyDefinitions;

    @Autowired
    public PropertyDefinitionServiceImpl(TelemetryPropertyDefinitionDao propertyDefinitionDao) {
        this.propertyDefinitionDao = propertyDefinitionDao;
        propertyDefinitions = new HashMap<>();
    }

    @PostConstruct
    public void readDataFromDatabase() {
        initializePropDefn();
        propertyDefinitionDao.findAll().forEach(def -> propertyDefinitions.put(def.getTelemetryPropertyName(), def));
    }

    @Override
    public void addTextProperty(List<TelemetryProperty> properties, String propertyName, String value, VehicleTelemetry telemetry) {
        if (value != null) {
            try {
                TelemetryProperty property = buildProperty(propertyName, telemetry);
                property.setTelemetryPropertyValueText(value);
                properties.add(property);
            } catch (ValidationException e) {
                log.debug("Skipping. Reason: " + e.getMessage());
            }
        } else {
            log.debug("Skipping empty property: " + propertyName);
        }
    }

    private TelemetryProperty buildProperty(String propertyName, VehicleTelemetry telemetry) throws ValidationException {
        TelemetryPropertyDefinition propertyDefinition = propertyDefinitions.get(propertyName);
        if (propertyDefinition == null) {
            throw new ValidationException("Non-existing property");
        }
        return TelemetryProperty.builder()
                .telemetryPropertyDefinition(propertyDefinition)
                .vehicleTelemetry(telemetry)
                .build();
    }

    @Override
    public void addDateProperty(List<TelemetryProperty> properties, String propertyName, Timestamp value, VehicleTelemetry telemetry) {
        if (value != null) {
            try {
                TelemetryProperty property = buildProperty(propertyName, telemetry);
                property.setTelemetryPropertyValueDate(value);
                properties.add(property);
            } catch (ValidationException e) {
                log.debug("Skipping. Reason: " + e.getMessage());
            }
        } else {
            log.debug("Skipping empty property: " + propertyName);
        }
    }

    @Override
    public void addIntProperty(List<TelemetryProperty> properties, String propertyName, Integer value, VehicleTelemetry telemetry) {
        if (value != null) {
            try {
                TelemetryProperty property = buildProperty(propertyName, telemetry);
                property.setTelemetryPropertyValueInteger(value);
                properties.add(property);
            } catch (ValidationException e) {
                log.debug("Skipping. Reason: " + e.getMessage());
            }
        } else {
            log.debug("Skipping empty property: " + propertyName);
        }
    }

    @Override
    public void addBooleanProperty(List<TelemetryProperty> properties, String propertyName, Boolean value, VehicleTelemetry telemetry) {
        if (value != null) {
            try {
                TelemetryProperty property = buildProperty(propertyName, telemetry);
                property.setTelemetryPropertyValueFlag(value);
                properties.add(property);
            } catch (ValidationException e) {
                log.debug("Skipping. Reason: " + e.getMessage());
            }
        } else {
            log.debug("Skipping empty property: " + propertyName);
        }
    }

    @Override
    public void addDoubleProperty(List<TelemetryProperty> properties, String propertyName, Double value, VehicleTelemetry telemetry) {
        if (value != null) {
            try {
                TelemetryProperty property = buildProperty(propertyName, telemetry);
                property.setTelemetryPropertyValueDecimal(value);
                properties.add(property);
            } catch (ValidationException e) {
                log.debug("Skipping. Reason: " + e.getMessage());
            }
        } else {
            log.debug("Skipping empty property: " + propertyName);
        }
    }

    @Override
    public TelemetryPropertyType getPropertyType(String propertyName) {
        TelemetryPropertyDefinition propertyDefinition = propertyDefinitions.get(propertyName);
        return propertyDefinition == null ? null : propertyDefinition.getTelemetryPropertyType();
    }

    @Override
    public Integer getPropertyKy(String propertyName) {
        TelemetryPropertyDefinition propertyDefinition = propertyDefinitions.get(propertyName);
        return propertyDefinition == null ? null : propertyDefinition.getTelemetryPropertyDefinitionKey();
    }

    @Override
    public PropertyFilter getProperty(String propertyName) {
        TelemetryPropertyDefinition propertyDefinition = propertyDefinitions.get(propertyName);
        return propertyDefinition == null ? null : PropertyFilter.copy(propertyDefinition);
    }

    /**
     * As DB is in memory this needs to be done every time on startup. Otherwise, it would be done once and that's it.
     */
    private void initializePropDefn() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/propertiesDefinition.csv"))) {
            String line;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                propertyDefinitionDao.save(new TelemetryPropertyDefinition(Integer.valueOf(fields[0].trim()), fields[2].trim(), valueOf(fields[1].trim())));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
