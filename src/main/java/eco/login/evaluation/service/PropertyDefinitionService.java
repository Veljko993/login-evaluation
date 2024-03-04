package eco.login.evaluation.service;

import eco.login.evaluation.common.TelemetryPropertyType;
import eco.login.evaluation.dao.entity.TelemetryProperty;
import eco.login.evaluation.dao.entity.VehicleTelemetry;
import eco.login.evaluation.model.PropertyFilter;

import java.sql.Timestamp;
import java.util.List;

/**
 * Service used for preparing properties for saving into DB
 */
public interface PropertyDefinitionService {
    /**
     * Adding the property to the relevant object in the expected format
     *
     * @param properties   - list of properties where the property will be added
     * @param propertyName
     * @param value
     * @param telemetry    - parent object
     */
    void addTextProperty(List<TelemetryProperty> properties, String propertyName, String value, VehicleTelemetry telemetry);

    /**
     * Adding the property to the relevant object in the expected format
     *
     * @param properties   - list of properties where the property will be added
     * @param propertyName
     * @param value
     * @param telemetry    - parent object
     */
    void addDateProperty(List<TelemetryProperty> properties, String propertyName, Timestamp value, VehicleTelemetry telemetry);

    /**
     * Adding the property to the relevant object in the expected format
     *
     * @param properties   - list of properties where the property will be added
     * @param propertyName
     * @param value
     * @param telemetry    - parent object
     */
    void addIntProperty(List<TelemetryProperty> properties, String propertyName, Integer value, VehicleTelemetry telemetry);

    /**
     * Adding the property to the relevant object in the expected format
     *
     * @param properties   - list of properties where the property will be added
     * @param propertyName
     * @param value
     * @param telemetry    - parent object
     */
    void addBooleanProperty(List<TelemetryProperty> properties, String propertyName, Boolean value, VehicleTelemetry telemetry);

    /**
     * Adding the property to the relevant object in the expected format
     *
     * @param properties   - list of properties where the property will be added
     * @param propertyName
     * @param value
     * @param telemetry    - parent object
     */
    void addDoubleProperty(List<TelemetryProperty> properties, String propertyName, Double value, VehicleTelemetry telemetry);

    TelemetryPropertyType getPropertyType(String propertyName);

    Integer getPropertyKy(String propertyName);

    PropertyFilter getProperty(String propertyName);

}
