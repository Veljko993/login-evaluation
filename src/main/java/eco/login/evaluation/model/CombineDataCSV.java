package eco.login.evaluation.model;

import lombok.Builder;
import lombok.Data;

/**
 * All string Combine data, used for reading from CSV
 */
@Builder
@Data
public class CombineDataCSV {
    String timestamp;
    String serialNumber;
    String gpsLong;
    String gpsLat;
    String totalWorkHrs;
    String groundSpeed;
    String engineSpeed;
    String engineLoad;
    String drumSpeed;
    String fanSpeed;
    String rotorStrawWalkerSpeed;
    String separationLosses;
    String sieveLosses;
    String chopper;
    String dieselTankLvl;
    String partialWidths;
    String frontAttachment;
    String maxPartialWidths;
    String feedRakeSpeed;
    String workingPosition;
    String grainTankUnloading;
    String mainDriveStatus;
    String concavePosition;
    String upperSievePosition;
    String lowerSievePosition;
    String grainTank70;
    String grainTank100;
    String throughput;
    String radialSpreaderSpeed;
    String grainInReturns;
    String channelPosition;
    String yieldMeasurement;
    String returnsAugerMeasurement;
    String moistureMeasurement;
    String typeOfCrop;
    String specificCropWeight;
    String autoPilotStat;
    String cruisePilotStat;
    String rateOfWork;
    String yield;
    String quantimeterCalibrationFactor;
    String separationSensitivity;
    String sieveSensitivity;
}
