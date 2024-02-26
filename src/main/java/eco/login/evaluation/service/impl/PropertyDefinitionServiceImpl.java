package eco.login.evaluation.service.impl;

import eco.login.evaluation.dao.entity.TelemetryProperty;
import eco.login.evaluation.dao.entity.TelemetryPropertyDefinition;
import eco.login.evaluation.dao.entity.VehicleTelemetry;
import eco.login.evaluation.dao.repository.TelemetryPropertyDefinitionDao;
import eco.login.evaluation.service.PropertyDefinitionService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        propertyDefinitionDao.findAll().forEach(def -> propertyDefinitions.put(def.getTelemetryPropertyName(), def));
    }

    @Override
    public void addTextProperty(List<TelemetryProperty> properties, String propertyName, String value, VehicleTelemetry telemetry) {
        if (value != null) {
            properties.add(TelemetryProperty.builder()
                    .telemetryPropertyDefinition(propertyDefinitions.get(propertyName))
                    .telemetryPropertyValueText(value)
                    .vehicleTelemetry(telemetry)
                    .build());
        } else {
            log.debug("Skipping empty property: " + propertyName);
        }
    }

    @Override
    public void addDateProperty(List<TelemetryProperty> properties, String propertyName, Timestamp value, VehicleTelemetry telemetry) {
        if (value != null) {
            properties.add(TelemetryProperty.builder()
                    .telemetryPropertyDefinition(propertyDefinitions.get(propertyName))
                    .telemetryPropertyValueDate(value)
                    .vehicleTelemetry(telemetry)
                    .build());
        } else {
            log.debug("Skipping empty property: " + propertyName);
        }
    }

    @Override
    public void addIntProperty(List<TelemetryProperty> properties, String propertyName, Integer value, VehicleTelemetry telemetry) {
        if (value != null) {
            properties.add(TelemetryProperty.builder()
                    .telemetryPropertyDefinition(propertyDefinitions.get(propertyName))
                    .telemetryPropertyValueInteger(value)
                    .vehicleTelemetry(telemetry)
                    .build());
        } else {
            log.debug("Skipping empty property: " + propertyName);
        }
    }

    @Override
    public void addBooleanProperty(List<TelemetryProperty> properties, String propertyName, Boolean value, VehicleTelemetry telemetry) {
        if (value != null) {
            properties.add(TelemetryProperty.builder()
                    .telemetryPropertyDefinition(propertyDefinitions.get(propertyName))
                    .telemetryPropertyValueFlag(value)
                    .vehicleTelemetry(telemetry)
                    .build());
        } else {
            log.debug("Skipping empty property: " + propertyName);
        }
    }

    @Override
    public void addDoubleProperty(List<TelemetryProperty> properties, String propertyName, Double value, VehicleTelemetry telemetry) {
        if (value != null) {
            properties.add(TelemetryProperty.builder()
                    .telemetryPropertyDefinition(propertyDefinitions.get(propertyName))
                    .telemetryPropertyValueDecimal(value)
                    .vehicleTelemetry(telemetry)
                    .build());
        } else {
            log.debug("Skipping empty property: " + propertyName);
        }
    }
}
