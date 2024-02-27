package eco.login.evaluation.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TractorDataCSV {

    @CsvBindByName(column = "Date/Time")
    String timestamp;
    @CsvBindByName(column = "Serial number")
    String serialNumber;
    @CsvBindByName(column = "GPS longitude [°]")
    String gpsLong;
    @CsvBindByName(column = "GPS latitude [°]")
    String gpsLat;
    @CsvBindByName(column = "Total working hours counter [h]")
    String totalWorkHrs;
    @CsvBindByName(column = "Engine speed [rpm]")
    String engineSpeed;
    @CsvBindByName(column = "Engine load [%]")
    String engineLoad;
    @CsvBindByName(column = "Fuel consumption [l/h]")
    String fuelConsumption;
    @CsvBindByName(column = "Ground speed gearbox [km/h]")
    String groundSpeedGearbox;
    @CsvBindByName(column = "Ground speed radar [km/h]")
    String groundSpeedRadar;
    @CsvBindByName(column = "Coolant temperature [°C]")
    String coolantTemp;
    @CsvBindByName(column = "Speed front PTO [rpm]")
    String speedFrontPTO;
    @CsvBindByName(column = "Speed rear PTO [rpm]")
    String speedRearPTO;
    @CsvBindByName(column = "current gear shift []")
    String currentGearShift;
    @CsvBindByName(column = "Ambient temperature [°C]")
    String ambientTemp;
    @CsvBindByName(column = "Parking brake status []")
    String parkingBrakeState;
    @CsvBindByName(column = "Transverse differential lock status []")
    String transDiffLockStat;
    @CsvBindByName(column = "All-wheel drive status []")
    String allWheelDriveStat;
    @CsvBindByName(column = "Actual status of creeper []")
    String actualStatOfCreep;
}
