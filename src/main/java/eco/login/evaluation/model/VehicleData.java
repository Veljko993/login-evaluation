package eco.login.evaluation.model;

import eco.login.evaluation.common.VehicleType;
import eco.login.evaluation.dao.entity.VehicleTelemetry;
import eco.login.evaluation.dao.repository.VehicleDao;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
@Builder
@Data
@Slf4j
public class VehicleData implements Serializable {
    String serialNumber;
    VehicleType vehicleType;
    Map<String, String> properties;

    public static VehicleData convert(VehicleTelemetry telemetry){
        Map<String, String> listOfProps = new HashMap<>();
        telemetry.getTelemetryProperties().forEach(prop -> listOfProps.put(prop.getTelemetryPropertyDefinition().getTelemetryPropertyName(), prop.getValueOf()));
        return VehicleData.builder().serialNumber(telemetry.getVehicle().getVehicleKey()).vehicleType(telemetry.getVehicle().getVehicleType())
                .properties(listOfProps).build();
    }
}
