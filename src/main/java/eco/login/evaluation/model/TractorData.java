package eco.login.evaluation.model;

import eco.login.evaluation.exception.ValidationException;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

import static eco.login.evaluation.common.ParseUtil.*;

/**
 * Model for Tractor data
 */
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
    String allWheelDriveStat;//TODO It has values Active, Inactive and 2, so not sure what is expected here. For now, I'll leave it whatever it is, and check with someone with domain knowledge
    Boolean actualStatOfCreep;

    /**
     * Method for converting all string object, into a specific Tractor object
     *
     * @param data - object containing all fields as strings
     * @return converted object if successful, otherwise null
     */
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
                    .allWheelDriveStat(data.getAllWheelDriveStat())
                    .actualStatOfCreep(parseBoolean(data.getActualStatOfCreep()))
                    .build();
        } catch (ValidationException | NumberFormatException e) {
            log.error("Parsing failed due to: " + e.getMessage());
            tractorData = null;
        }
        return tractorData;
    }
}
