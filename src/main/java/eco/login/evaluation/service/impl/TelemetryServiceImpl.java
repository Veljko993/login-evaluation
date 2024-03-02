package eco.login.evaluation.service.impl;

import eco.login.evaluation.common.VehicleType;
import eco.login.evaluation.dao.entity.TelemetryProperty;
import eco.login.evaluation.dao.entity.Vehicle;
import eco.login.evaluation.dao.entity.VehicleTelemetry;
import eco.login.evaluation.dao.repository.VehicleDao;
import eco.login.evaluation.exception.ValidationException;
import eco.login.evaluation.model.CombineData;
import eco.login.evaluation.model.TractorData;
import eco.login.evaluation.service.PropertyDefinitionService;
import eco.login.evaluation.service.TelemetryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static eco.login.evaluation.common.PropertyNamesConst.*;

@Service
public class TelemetryServiceImpl implements TelemetryService {
    private final VehicleDao vehicleDao;
    private final PropertyDefinitionService propertyDefinitionService;

    public TelemetryServiceImpl(VehicleDao vehicleDao, PropertyDefinitionService propertyDefinitionService) {
        this.vehicleDao = vehicleDao;
        this.propertyDefinitionService = propertyDefinitionService;
    }

    @Override
    public int saveTractorData(List<TractorData> parsedData) throws ValidationException {
        String serialNum = parsedData.get(0).getSerialNumber();
        Vehicle vehicle = vehicleDao.findById(serialNum).orElse(new Vehicle(serialNum, VehicleType.TRACTOR, new ArrayList<>()));
        int counter = 0;
        for (TractorData data : parsedData) {
            if (serialNum.equals(data.getSerialNumber())) {
                VehicleTelemetry telemetry = VehicleTelemetry.builder()
                        .vehicle(vehicle)
                        .build();
                telemetry.setTelemetryProperties(populateTelemetryProperties(data, telemetry));
                vehicle.getVehicleTelemetries().add(telemetry);
                counter++;
            } else {
                throw new ValidationException("Data in single file should be only for one vehicle.");
            }
        }
        vehicleDao.save(vehicle);
        return counter;
    }

    private List<TelemetryProperty> populateTelemetryProperties(TractorData data, VehicleTelemetry telemetry) {
        List<TelemetryProperty> properties = new ArrayList<>();
        propertyDefinitionService.addDateProperty(properties, TIMESTAMP, data.getTimestamp(), telemetry);
        propertyDefinitionService.addTextProperty(properties, SERIAL_NUMBER, data.getSerialNumber(), telemetry);
        propertyDefinitionService.addTextProperty(properties, GPS_LONGITUDE, data.getGpsLong(), telemetry);
        propertyDefinitionService.addTextProperty(properties, GPS_LATITUDE, data.getGpsLat(), telemetry);
        propertyDefinitionService.addTextProperty(properties, GROUND_SPEED_RADAR, data.getGroundSpeedRadar(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, TOTAL_WORKING_HOURS, data.getTotalWorkHrs(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, AMBIENT_TEMPERATURE, data.getAmbientTemp(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, FUEL_CONSUMPTION, data.getFuelConsumption(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, GROUND_SPEED_GEARBOX, data.getGroundSpeedGearbox(), telemetry);
        propertyDefinitionService.addIntProperty(properties, ENGINE_SPEED, data.getEngineSpeed(), telemetry);
        propertyDefinitionService.addIntProperty(properties, ENGINE_LOAD, data.getEngineLoad(), telemetry);
        propertyDefinitionService.addIntProperty(properties, COOLANT_TEMPERATURE, data.getCoolantTemp(), telemetry);
        propertyDefinitionService.addIntProperty(properties, SPEED_FRONT_PTO, data.getSpeedFrontPTO(), telemetry);
        propertyDefinitionService.addIntProperty(properties, SPEED_REAR_PTO, data.getSpeedRearPTO(), telemetry);
        propertyDefinitionService.addIntProperty(properties, CURRENT_GEAR_SHIFT, data.getCurrentGearShift(), telemetry);
        propertyDefinitionService.addIntProperty(properties, PARKING_BRAKE_STATE, data.getParkingBrakeState(), telemetry);
        propertyDefinitionService.addBooleanProperty(properties, ACTUAL_STATUS_OF_CREEPER, data.getActualStatOfCreep(), telemetry);
        propertyDefinitionService.addBooleanProperty(properties, TRANSVERSE_DIFFERENTIAL_LOCK_STATUS, data.getTransDiffLockStat(), telemetry);
        propertyDefinitionService.addTextProperty(properties, ALL_WHEEL_DRIVE_STATUS, data.getAllWheelDriveStat(), telemetry);
        return properties;
    }

    @Override
    public int saveCombineData(List<CombineData> parsedData) throws ValidationException {
        String serialNum = parsedData.get(0).getSerialNumber();
        Vehicle vehicle = vehicleDao.findById(serialNum).orElse(new Vehicle(serialNum, VehicleType.COMBINE, new ArrayList<>()));
        int counter = 0;
        for (CombineData data : parsedData) {
            if (serialNum.equals(data.getSerialNumber())) {
                VehicleTelemetry telemetry = VehicleTelemetry.builder()
                        .vehicle(vehicle)
                        .build();
                telemetry.setTelemetryProperties(populateTelemetryProperties(data, telemetry));
                vehicle.getVehicleTelemetries().add(telemetry);
                counter++;
            } else {
                throw new ValidationException("Data in single file should be only for one vehicle.");
            }
        }
        vehicleDao.save(vehicle);
        return counter;
    }

    private List<TelemetryProperty> populateTelemetryProperties(CombineData data, VehicleTelemetry telemetry) {
        List<TelemetryProperty> properties = new ArrayList<>();
        propertyDefinitionService.addDateProperty(properties, TIMESTAMP, data.getTimestamp(), telemetry);
        propertyDefinitionService.addTextProperty(properties, SERIAL_NUMBER, data.getSerialNumber(), telemetry);
        propertyDefinitionService.addTextProperty(properties, GPS_LONGITUDE, data.getGpsLong(), telemetry);
        propertyDefinitionService.addTextProperty(properties, GPS_LATITUDE, data.getGpsLat(), telemetry);
        propertyDefinitionService.addTextProperty(properties, TYPE_OF_CROP, data.getTypeOfCrop(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, TOTAL_WORKING_HOURS, data.getTotalWorkHrs(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, GROUND_SPEED, data.getGroundSpeed(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, SEPARATION_LOSSES, data.getSeparationLosses(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, SIEVE_LOSSES, data.getSieveLosses(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, DIESEL_TANK_LEVEL, data.getDieselTankLvl(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, GRAIN_IN_RETURNS, data.getGrainInReturns(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, CHANNEL_POSITION, data.getChannelPosition(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, RETURNS_AUGER_MEASUREMENT, data.getReturnsAugerMeasurement(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, SPECIFIC_CROP_WEIGHT, data.getSpecificCropWeight(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, THROUGHPUT, data.getThroughput(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, RATE_OF_WORK, data.getRateOfWork(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, YIELD, data.getYield(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, QUANTIMETER_CALIBRATION_FACTOR, data.getQuantimeterCalibrationFactor(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, SEPARATION_SENSITIVITY, data.getSeparationSensitivity(), telemetry);
        propertyDefinitionService.addDoubleProperty(properties, SIEVE_SENSITIVITY, data.getSieveSensitivity(), telemetry);
        propertyDefinitionService.addIntProperty(properties, ENGINE_SPEED, data.getEngineSpeed(), telemetry);
        propertyDefinitionService.addIntProperty(properties, ENGINE_LOAD, data.getEngineLoad(), telemetry);
        propertyDefinitionService.addIntProperty(properties, DRUM_SPEED, data.getDrumSpeed(), telemetry);
        propertyDefinitionService.addIntProperty(properties, FAN_SPEED, data.getFanSpeed(), telemetry);
        propertyDefinitionService.addIntProperty(properties, ROTOR_STRAW_WALKER_SPEED, data.getRotorStrawWalkerSpeed(), telemetry);
        propertyDefinitionService.addIntProperty(properties, PARTIAL_WIDTHS, data.getPartialWidths(), telemetry);
        propertyDefinitionService.addIntProperty(properties, MAX_PARTIAL_WIDTHS, data.getMaxPartialWidths(), telemetry);
        propertyDefinitionService.addIntProperty(properties, FEED_RAKE_SPEED, data.getFeedRakeSpeed(), telemetry);
        propertyDefinitionService.addIntProperty(properties, CONCAVE_POSITION, data.getConcavePosition(), telemetry);
        propertyDefinitionService.addIntProperty(properties, UPPER_SIEVE_POSITION, data.getUpperSievePosition(), telemetry);
        propertyDefinitionService.addIntProperty(properties, CRUISE_PILOT_STATUS, data.getCruisePilotStat(), telemetry);
        propertyDefinitionService.addIntProperty(properties, LOWER_SIEVE_POSITION, data.getLowerSievePosition(), telemetry);
        propertyDefinitionService.addIntProperty(properties, RADIAL_SPREADER_SPEED, data.getRadialSpreaderSpeed(), telemetry);
        propertyDefinitionService.addBooleanProperty(properties, WORKING_POSITION, data.getWorkingPosition(), telemetry);
        propertyDefinitionService.addBooleanProperty(properties, GRAIN_TANK_UNLOADING, data.getGrainTankUnloading(), telemetry);
        propertyDefinitionService.addBooleanProperty(properties, MAIN_DRIVE_STATUS, data.getMainDriveStatus(), telemetry);
        propertyDefinitionService.addBooleanProperty(properties, FRONT_ATTACHMENT, data.getFrontAttachment(), telemetry);
        propertyDefinitionService.addBooleanProperty(properties, GRAIN_TANK_70, data.getGrainTank70(), telemetry);
        propertyDefinitionService.addBooleanProperty(properties, GRAIN_TANK_100, data.getGrainTank100(), telemetry);
        propertyDefinitionService.addBooleanProperty(properties, YIELD_MEASUREMENT, data.getYieldMeasurement(), telemetry);
        propertyDefinitionService.addBooleanProperty(properties, MOISTURE_MEASUREMENT, data.getMoistureMeasurement(), telemetry);
        propertyDefinitionService.addBooleanProperty(properties, AUTO_PILOT_STATUS, data.getAutoPilotStat(), telemetry);
        propertyDefinitionService.addBooleanProperty(properties, CHOPPER, data.getChopper(), telemetry);
        return properties;
    }
}
