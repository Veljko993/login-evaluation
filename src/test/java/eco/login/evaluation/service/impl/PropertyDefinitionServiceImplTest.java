package eco.login.evaluation.service.impl;

import eco.login.evaluation.dao.entity.TelemetryProperty;
import eco.login.evaluation.dao.entity.TelemetryPropertyDefinition;
import eco.login.evaluation.dao.entity.VehicleTelemetry;
import eco.login.evaluation.dao.repository.TelemetryPropertyDefinitionDao;
import eco.login.evaluation.model.PropertyFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static eco.login.evaluation.common.PropertyNamesConst.*;
import static eco.login.evaluation.common.TelemetryPropertyType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PropertyDefinitionServiceImplTest {

    private PropertyDefinitionServiceImpl service;
    @Mock
    private TelemetryPropertyDefinitionDao propertyDefinitionDao;

    private TelemetryPropertyDefinition serialNumberProp;

    @BeforeEach
    public void setUp() {
        service = new PropertyDefinitionServiceImpl(propertyDefinitionDao);
        when(propertyDefinitionDao.save(any())).thenReturn(null);
        List<TelemetryPropertyDefinition> propDefnList = getPropertyDefinitions();
        when(propertyDefinitionDao.findAll()).thenReturn(propDefnList);
        service.readDataFromDatabase();
    }

    private List<TelemetryPropertyDefinition> getPropertyDefinitions() {
        List<TelemetryPropertyDefinition> propDefnList = new ArrayList<>();
        serialNumberProp = new TelemetryPropertyDefinition(1, SERIAL_NUMBER, TEXT);
        propDefnList.add(serialNumberProp);
        propDefnList.add(new TelemetryPropertyDefinition(2, TIMESTAMP, DATE));
        propDefnList.add(new TelemetryPropertyDefinition(3, ENGINE_SPEED, INT));
        propDefnList.add(new TelemetryPropertyDefinition(4, CHOPPER, BOOLEAN));
        propDefnList.add(new TelemetryPropertyDefinition(5, TOTAL_WORKING_HOURS, DOUBLE));
        return propDefnList;
    }


    @Test
    public void addTextPropertyTest() {
        List<TelemetryProperty> properties = new ArrayList<>();
        VehicleTelemetry vehicleTelemetry = new VehicleTelemetry();
        String serialNumber = "A003";
        service.addTextProperty(properties, SERIAL_NUMBER, serialNumber, vehicleTelemetry);
        assertEquals(1, properties.size());
        TelemetryProperty telemetryProperty = properties.get(0);
        String propertyName = telemetryProperty.getTelemetryPropertyDefinition().getTelemetryPropertyName();
        assertEquals(SERIAL_NUMBER, propertyName);
        assertEquals(serialNumber, telemetryProperty.getTelemetryPropertyValueText());
        service.addTextProperty(properties, SERIAL_NUMBER, null, vehicleTelemetry);
        service.addTextProperty(properties, "DummyProp", serialNumber, vehicleTelemetry);
        assertEquals(1, properties.size());
    }

    @Test
    public void addDatePropertyTest() {
        List<TelemetryProperty> properties = new ArrayList<>();
        VehicleTelemetry vehicleTelemetry = new VehicleTelemetry();
        Timestamp timestamp = new Timestamp(0);
        service.addDateProperty(properties, TIMESTAMP, timestamp, vehicleTelemetry);
        assertEquals(1, properties.size());
        TelemetryProperty telemetryProperty = properties.get(0);
        String propertyName = telemetryProperty.getTelemetryPropertyDefinition().getTelemetryPropertyName();
        assertEquals(TIMESTAMP, propertyName);
        assertEquals(timestamp, telemetryProperty.getTelemetryPropertyValueDate());
        service.addDateProperty(properties, TIMESTAMP, null, vehicleTelemetry);
        service.addDateProperty(properties, "DummyProp", timestamp, vehicleTelemetry);
        assertEquals(1, properties.size());
    }

    @Test
    public void addIntPropertyTest() {
        List<TelemetryProperty> properties = new ArrayList<>();
        VehicleTelemetry vehicleTelemetry = new VehicleTelemetry();
        int engineSpeed = 100;
        service.addIntProperty(properties, ENGINE_SPEED, engineSpeed, vehicleTelemetry);
        assertEquals(1, properties.size());
        TelemetryProperty telemetryProperty = properties.get(0);
        String propertyName = telemetryProperty.getTelemetryPropertyDefinition().getTelemetryPropertyName();
        assertEquals(ENGINE_SPEED, propertyName);
        assertEquals(engineSpeed, telemetryProperty.getTelemetryPropertyValueInteger());
        service.addIntProperty(properties, ENGINE_SPEED, null, vehicleTelemetry);
        service.addIntProperty(properties, "dummyProp", engineSpeed, vehicleTelemetry);
        assertEquals(1, properties.size());
    }

    @Test
    public void addDoublePropertyTest() {
        List<TelemetryProperty> properties = new ArrayList<>();
        VehicleTelemetry vehicleTelemetry = new VehicleTelemetry();
        Double totWorkHrs = 1234.56;
        service.addDoubleProperty(properties, TOTAL_WORKING_HOURS, totWorkHrs, vehicleTelemetry);
        assertEquals(1, properties.size());
        TelemetryProperty telemetryProperty = properties.get(0);
        String propertyName = telemetryProperty.getTelemetryPropertyDefinition().getTelemetryPropertyName();
        assertEquals(TOTAL_WORKING_HOURS, propertyName);
        assertEquals(totWorkHrs, telemetryProperty.getTelemetryPropertyValueDecimal());
        service.addDoubleProperty(properties, TOTAL_WORKING_HOURS, null, vehicleTelemetry);
        service.addDoubleProperty(properties, "dummyProp", totWorkHrs, vehicleTelemetry);
        assertEquals(1, properties.size());
    }

    @Test
    public void addBooleanPropertyTest() {
        List<TelemetryProperty> properties = new ArrayList<>();
        VehicleTelemetry vehicleTelemetry = new VehicleTelemetry();
        service.addBooleanProperty(properties, CHOPPER, true, vehicleTelemetry);
        assertEquals(1, properties.size());
        TelemetryProperty telemetryProperty = properties.get(0);
        String propertyName = telemetryProperty.getTelemetryPropertyDefinition().getTelemetryPropertyName();
        assertEquals(CHOPPER, propertyName);
        assertTrue(telemetryProperty.getTelemetryPropertyValueFlag());
        service.addBooleanProperty(properties, CHOPPER, null, vehicleTelemetry);
        service.addBooleanProperty(properties, "dummyProp", true, vehicleTelemetry);
        assertEquals(1, properties.size());
    }


    @Test
    public void testGetPropType() {
        assertEquals(serialNumberProp.getTelemetryPropertyType(), service.getPropertyType(SERIAL_NUMBER));
        assertNull(service.getPropertyType("MyWorkingHours"));
    }

    @Test
    public void testGetPropKy() {
        assertEquals(serialNumberProp.getTelemetryPropertyDefinitionKey(), service.getPropertyKy(SERIAL_NUMBER));
        assertNull(service.getPropertyKy("MyWorkingHours"));
    }

    @Test
    public void testGetProp() {
        PropertyFilter property = service.getProperty(SERIAL_NUMBER);
        assertEquals(serialNumberProp.getTelemetryPropertyDefinitionKey(), property.getPropertyKy());
        assertEquals(serialNumberProp.getTelemetryPropertyName(), property.getPropertyName());
        assertEquals(serialNumberProp.getTelemetryPropertyType(), property.getPropertyType());
        assertNull(service.getProperty("MyWorkingHours"));
    }


}
