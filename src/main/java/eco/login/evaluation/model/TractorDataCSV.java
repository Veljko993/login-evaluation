package eco.login.evaluation.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TractorDataCSV {

    String timestamp;
    String serialNumber;
    String gpsLong;
    String gpsLat;
    String totalWorkHrs;
    String engineSpeed;
    String engineLoad;
    String fuelConsumption;
    String groundSpeedGearbox;
    String groundSpeedRadar;
    String coolantTemp;
    String speedFrontPTO;
    String speedRearPTO;
    String currentGearShift;
    String ambientTemp;
    String parkingBrakeState;
    String transDiffLockStat;
    String allWheelDriveStat;
    String actualStatOfCreep;
}
