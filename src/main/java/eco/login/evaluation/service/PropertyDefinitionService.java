package eco.login.evaluation.service;

import eco.login.evaluation.dao.entity.TelemetryProperty;
import eco.login.evaluation.dao.entity.VehicleTelemetry;

import java.sql.Timestamp;
import java.util.List;

public interface PropertyDefinitionService {
    void addTextProperty(List<TelemetryProperty> properties, String propertyName, String value, VehicleTelemetry telemetry);

    void addDateProperty(List<TelemetryProperty> properties, String propertyName, Timestamp value, VehicleTelemetry telemetry);

    void addIntProperty(List<TelemetryProperty> properties, String propertyName, Integer value, VehicleTelemetry telemetry);

    void addBooleanProperty(List<TelemetryProperty> properties, String propertyName, Boolean value, VehicleTelemetry telemetry);

    void addDoubleProperty(List<TelemetryProperty> properties, String propertyName, Double value, VehicleTelemetry telemetry);
}
