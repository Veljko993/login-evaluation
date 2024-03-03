package eco.login.evaluation.service.impl;

import eco.login.evaluation.exception.FileReadingException;
import eco.login.evaluation.model.CombineData;
import eco.login.evaluation.model.CombineDataCSV;
import eco.login.evaluation.model.TractorData;
import eco.login.evaluation.model.TractorDataCSV;
import eco.login.evaluation.service.ParseCSVService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static eco.login.evaluation.common.CSVHeaderConst.*;

@Service
@Slf4j
public class ParseCSVServiceImpl implements ParseCSVService {
    @Override
    public List<TractorData> parseTractorData(List<TractorDataCSV> csvData) {
        List<TractorData> data = new ArrayList<>();
        for (TractorDataCSV csv : csvData) {
            TractorData converted = TractorData.convert(csv);
            if (converted != null) {
                data.add(converted);
            }
        }
        return data;
    }

    @Override
    public List<CombineData> parseCombineData(List<CombineDataCSV> csvData) {
        List<CombineData> data = new ArrayList<>();
        for (CombineDataCSV csv : csvData) {
            CombineData converted = CombineData.convert(csv);
            if (converted != null) {
                data.add(converted);
            }
        }
        return data;
    }

    @Override
    public List<TractorDataCSV> parseTractorCSV(MultipartFile file) throws FileReadingException {
        List<TractorDataCSV> data = new ArrayList<>();
        try (Reader in = new InputStreamReader(file.getInputStream())) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreSurroundingSpaces()
                    .withDelimiter(';')
                    .parse(in);
            for (CSVRecord record : records) {
                data.add(getTractorDataCSV(record));
            }
        } catch (IOException e) {
            throw new FileReadingException("Failed reading and parsing data from file  " + file.getOriginalFilename() + ") with message: " + e.getMessage(), e);
        }
        return data;
    }

    private TractorDataCSV getTractorDataCSV(CSVRecord record) {
        return TractorDataCSV.builder()
                .timestamp(record.get(TIMESTAMP))
                .serialNumber(record.get(SERIAL_NUMBER))
                .gpsLat(record.get(GPS_LATITUDE))
                .gpsLong(record.get(GPS_LONGITUDE))
                .totalWorkHrs(record.get(TOTAL_WORKING_HOURS_COUNTER_H))
                .engineSpeed(record.get(ENGINE_SPEED_RPM))
                .engineLoad(record.get(ENGINE_LOAD))
                .fuelConsumption(record.get(FUEL_CONSUMPTION_L_H))
                .groundSpeedGearbox(record.get(GROUND_SPEED_GEARBOX_KM_H))
                .groundSpeedRadar(record.get(GROUND_SPEED_RADAR_KM_H))
                .coolantTemp(record.get(COOLANT_TEMPERATURE_C))
                .speedFrontPTO(record.get(SPEED_FRONT_PTO_RPM))
                .speedRearPTO(record.get(SPEED_REAR_PTO_RPM))
                .currentGearShift(record.get(CURRENT_GEAR_SHIFT))
                .ambientTemp(record.get(AMBIENT_TEMPERATURE_C))
                .parkingBrakeState(record.get(PARKING_BRAKE_STATUS))
                .transDiffLockStat(record.get(TRANSVERSE_DIFFERENTIAL_LOCK_STATUS))
                .allWheelDriveStat(record.get(ALL_WHEEL_DRIVE_STATUS))
                .actualStatOfCreep(record.get(ACTUAL_STATUS_OF_CREEPER))
                .build();
    }

    @Override
    public List<CombineDataCSV> parseCombineCSV(MultipartFile file) throws FileReadingException {
        List<CombineDataCSV> data = new ArrayList<>();
        try (Reader in = new InputStreamReader(file.getInputStream())) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreSurroundingSpaces()
                    .withDelimiter(';')
                    .parse(in);
            for (CSVRecord record : records) {
                data.add(getCombineDataCSV(record));
            }
        } catch (IOException e) {
            throw new FileReadingException("Failed reading and parsing data from file  " + file.getOriginalFilename() + ") with message: " + e.getMessage(), e);
        }
        return data;
    }

    private CombineDataCSV getCombineDataCSV(CSVRecord record) {
        return CombineDataCSV.builder()
                .timestamp(record.get(TIMESTAMP))
                .serialNumber(record.get(SERIAL_NUMBER))
                .gpsLat(record.get(GPS_LATITUDE))
                .gpsLong(record.get(GPS_LONGITUDE))
                .totalWorkHrs(record.get(TOTAL_WORKING_HOURS_COUNTER_H))
                .engineSpeed(record.get(ENGINE_SPEED_RPM))
                .engineLoad(record.get(ENGINE_LOAD))
                .drumSpeed(record.get(DRUM_SPEED))
                .fanSpeed(record.get(FAN_SPEED))
                .rotorStrawWalkerSpeed(record.get(ROTOR_SPEED))
                .separationLosses(record.get(SEPARATION_LOSSES))
                .sieveLosses(record.get(SIEVE_LOSSES))
                .chopper(record.get(CHOPPER))
                .dieselTankLvl(record.get(DIESEL_TANK_LEVEL))
                .partialWidths(record.get(NO_PARTIAL_WIDTHS))
                .frontAttachment(record.get(FRONT_ATTACHMENT))
                .maxPartialWidths(record.get(MAX_NO_PARTIAL_WIDTH))
                .feedRakeSpeed(record.get(FEED_RAKE_SPEED))
                .workingPosition(record.get(WORKING_POSITION))
                .grainTankUnloading(record.get(GRAIN_TANK_UNLOADING))
                .mainDriveStatus(record.get(MAIN_DRIVE_STATUS))
                .concavePosition(record.get(CONCAVE_POSITION))
                .upperSievePosition(record.get(UPPER_SIEVE_POSITION))
                .lowerSievePosition(record.get(LOWER_SIEVE_PROSITION))
                .grainTank70(record.get(GRAIN_TANK_70))
                .grainTank100(record.get(GRAIN_TANK_100))
                .throughput(record.get(THROUGHPUT))
                .radialSpreaderSpeed(record.get(RADIAL_SPEADER_SPEED))
                .grainInReturns(record.get(GRAIN_IN_RETURNS))
                .channelPosition(record.get(CHANNEL_POSITION))
                .yieldMeasurement(record.get(YIELD_MEASUREMENT))
                .returnsAugerMeasurement(record.get(RETURNS_AUGER_MEASUREMENT))
                .moistureMeasurement(record.get(MOISTURE_MEASUREMENT))
                .typeOfCrop(record.get(TYPE_OF_CROP))
                .specificCropWeight(record.get(SPECIFIC_CROP_WEIGHT))
                .autoPilotStat(record.get(AUTO_PILOT_STATUS))
                .cruisePilotStat(record.get(CRUISE_PILOT_STATUS))
                .rateOfWork(record.get(RATE_OF_WORK))
                .yield(record.get(YIELD))
                .quantimeterCalibrationFactor(record.get(QUANT_CALIBRATION_FACTOR))
                .separationSensitivity(record.get(SEPARATION_SENSITIVITY))
                .sieveSensitivity(record.get(SIEVE_SENSITIVITY))
                .build();
    }


}
