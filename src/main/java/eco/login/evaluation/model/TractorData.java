package eco.login.evaluation.model;

import eco.login.evaluation.exception.ValidationException;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

import static eco.login.evaluation.common.ParseUtil.*;

@Builder
@Data
@Slf4j
public class TractorData {

    Timestamp timestamp;
    String serialNumber;
    String gpsLong;
    String gpsLat;
    String groundSpeedRadar;
    Double totalWorkHrs;
    Double ambientTemp;
    Double fuelConsumption;
    Double groundSpeedGearbox;
    Integer engineSpeed;
    Integer engineLoad;
    Integer coolantTemp;
    Integer speedFrontPTO;
    Integer speedRearPTO;
    Integer currentGearShift;
    Integer parkingBrakeState;
    Boolean transDiffLockStat;
    Boolean allWheelDriveStat;
    Boolean actualStatOfCreep;

    public static TractorData convert(TractorDataCSV data) {
        TractorData tractorData;
        try {
            tractorData = TractorData.builder()
                    .serialNumber(data.getSerialNumber())
                    .gpsLat(data.getGpsLat())
                    .gpsLong(data.getGpsLong())
                    .groundSpeedRadar(data.getGroundSpeedRadar())
                    .timestamp(parseTimestamp(data.getTimestamp()))
                    .totalWorkHrs(parseDouble(data.getTotalWorkHrs()))
                    .groundSpeedGearbox(parseDouble(data.getGroundSpeedGearbox()))
                    .ambientTemp(parseDouble(data.getAmbientTemp()))
                    .fuelConsumption(parseDouble(data.getFuelConsumption()))
                    .engineSpeed(parseInt(data.getEngineSpeed()))
                    .engineLoad(parseInt(data.getEngineLoad()))
                    .coolantTemp(parseInt(data.getCoolantTemp()))
                    .speedFrontPTO(parseInt(data.getSpeedFrontPTO()))
                    .speedRearPTO(parseInt(data.getSpeedRearPTO()))
                    .currentGearShift(parseInt(data.getCurrentGearShift()))
                    .parkingBrakeState(parseInt(data.getParkingBrakeState()))
                    .transDiffLockStat(parseBoolean(data.getTransDiffLockStat()))
                    .allWheelDriveStat(parseBoolean(data.getAllWheelDriveStat()))
                    .actualStatOfCreep(parseBoolean(data.getActualStatOfCreep()))
                    .build();
        } catch (ValidationException e) {
            log.error("Parsing failed due to: " + e.getMessage());
            tractorData = null;
        }
        return tractorData;
    }
}
