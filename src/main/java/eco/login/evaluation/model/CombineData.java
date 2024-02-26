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
public class CombineData {
    Timestamp timestamp;
    String serialNumber;
    String gpsLong;
    String gpsLat;
    String typeOfCrop;
    Double totalWorkHrs;
    Double groundSpeed;
    Double separationLosses;
    Double sieveLosses;
    Double dieselTankLvl;
    Double grainInReturns;
    Double channelPosition;
    Double returnsAugerMeasurement;
    Double specificCropWeight;
    Double throughput;
    Double rateOfWork;
    Double yield;
    Double quantimeterCalibrationFactor;
    Double separationSensitivity;
    Double sieveSensitivity;
    Integer engineSpeed;
    Integer engineLoad;
    Integer drumSpeed;
    Integer fanSpeed;
    Integer rotorStrawWalkerSpeed;
    Integer partialWidths;
    Integer maxPartialWidths;
    Integer feedRakeSpeed;
    Integer concavePosition;
    Integer upperSievePosition;
    Integer cruisePilotStat;
    Integer lowerSievePosition;
    Integer radialSpreaderSpeed;
    Boolean workingPosition;
    Boolean grainTankUnloading;
    Boolean mainDriveStatus;
    Boolean frontAttachment;
    Boolean grainTank70;
    Boolean grainTank100;
    Boolean yieldMeasurement;
    Boolean moistureMeasurement;
    Boolean autoPilotStat;
    Boolean chopper;

    public static CombineData convert(CombineDataCSV data) {
        CombineData combineData;
        try {
            combineData = CombineData.builder()
                    .timestamp(parseTimestamp(data.getTimestamp()))
                    .serialNumber(data.getSerialNumber())
                    .gpsLat(data.getGpsLat())
                    .gpsLong(data.getGpsLong())
                    .typeOfCrop(data.getTypeOfCrop())
                    .totalWorkHrs(parseDouble(data.getTotalWorkHrs()))
                    .groundSpeed(parseDouble(data.getGroundSpeed()))
                    .separationLosses(parseDouble(data.getSeparationLosses()))
                    .sieveLosses(parseDouble(data.getSieveLosses()))
                    .dieselTankLvl(parseDouble(data.getDieselTankLvl()))
                    .grainInReturns(parseDouble(data.getGrainInReturns()))
                    .channelPosition(parseDouble(data.getChannelPosition()))
                    .returnsAugerMeasurement(parseDouble(data.getReturnsAugerMeasurement()))
                    .specificCropWeight(parseDouble(data.getSpecificCropWeight()))
                    .throughput(parseDouble(data.getThroughput()))
                    .rateOfWork(parseDouble(data.getRateOfWork()))
                    .yield(parseDouble(data.getYield()))
                    .quantimeterCalibrationFactor(parseDouble(data.getQuantimeterCalibrationFactor()))
                    .separationSensitivity(parseDouble(data.getSeparationSensitivity()))
                    .sieveSensitivity(parseDouble(data.getSieveSensitivity()))
                    .engineSpeed(parseInt(data.getEngineSpeed()))
                    .engineLoad(parseInt(data.getEngineLoad()))
                    .drumSpeed(parseInt(data.getDrumSpeed()))
                    .fanSpeed(parseInt(data.getFanSpeed()))
                    .rotorStrawWalkerSpeed(parseInt(data.getRotorStrawWalkerSpeed()))
                    .partialWidths(parseInt(data.getPartialWidths()))
                    .maxPartialWidths(parseInt(data.getMaxPartialWidths()))
                    .feedRakeSpeed(parseInt(data.getFeedRakeSpeed()))
                    .concavePosition(parseInt(data.getConcavePosition()))
                    .upperSievePosition(parseInt(data.getUpperSievePosition()))
                    .cruisePilotStat(parseInt(data.getCruisePilotStat()))
                    .lowerSievePosition(parseInt(data.getLowerSievePosition()))
                    .radialSpreaderSpeed(parseInt(data.getRadialSpreaderSpeed()))
                    .workingPosition(parseBoolean(data.getWorkingPosition()))
                    .grainTankUnloading(parseBoolean(data.getGrainTankUnloading()))
                    .mainDriveStatus(parseBoolean(data.getMainDriveStatus()))
                    .frontAttachment(parseBoolean(data.getFrontAttachment()))
                    .grainTank70(parseBoolean(data.getGrainTank70()))
                    .grainTank100(parseBoolean(data.getGrainTank100()))
                    .yieldMeasurement(parseBoolean(data.getYieldMeasurement()))
                    .moistureMeasurement(parseBoolean(data.getMoistureMeasurement()))
                    .autoPilotStat(parseBoolean(data.getAutoPilotStat()))
                    .chopper(parseBoolean(data.getChopper()))
                    .build();
        } catch (ValidationException e) {
            log.error("Parsing failed due to: " + e.getMessage());
            combineData = null;
        }
        return combineData;
    }
}
