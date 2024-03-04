package eco.login.evaluation.service.impl;

import eco.login.evaluation.dao.entity.Vehicle;
import eco.login.evaluation.dao.repository.VehicleDao;
import eco.login.evaluation.exception.ValidationException;
import eco.login.evaluation.model.CombineData;
import eco.login.evaluation.model.TractorData;
import eco.login.evaluation.service.PropertyDefinitionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TelemetryServiceImplTest {

    @Mock
    private PropertyDefinitionService definitionService;
    @Mock
    private VehicleDao vehicleDao;
    private TelemetryServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new TelemetryServiceImpl(vehicleDao, definitionService);
    }

    @Test
    public void saveDataEmptyDataTest() throws ValidationException {
        assertEquals(0, service.saveTractorData(new ArrayList<TractorData>()));
        assertEquals(0, service.saveTractorData(null));
        assertEquals(0, service.saveCombineData(new ArrayList<>()));
        assertEquals(0, service.saveCombineData(null));

    }

    @Test
    public void saveTractorDataTest() throws ValidationException {
        List<TractorData> data = new ArrayList<>();
        TractorData tractor1 = TractorData.builder().serialNumber("A003").build();
        data.add(tractor1);
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVehicleKey(tractor1.getSerialNumber());
        vehicle1.setVehicleTelemetries(new ArrayList<>());
        when(vehicleDao.findById(tractor1.getSerialNumber())).thenReturn(Optional.of(vehicle1));
        when(vehicleDao.save(vehicle1)).thenReturn(vehicle1);
        assertEquals(1, service.saveTractorData(data));
    }

    @Test
    public void saveTractorDataBadSecondDataTest() {
        List<TractorData> data = new ArrayList<>();
        TractorData tractor1 = TractorData.builder().serialNumber("A003").build();
        data.add(tractor1);
        TractorData tractor2 = TractorData.builder().serialNumber("A004").build();
        data.add(tractor2);
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVehicleKey(tractor1.getSerialNumber());
        vehicle1.setVehicleTelemetries(new ArrayList<>());
        when(vehicleDao.findById(tractor1.getSerialNumber())).thenReturn(Optional.of(vehicle1));
        try {
            service.saveTractorData(data);
            fail("Exception expected!");
        } catch (ValidationException e) {
            assertEquals("Data in single file should be only for one vehicle.", e.getMessage());
        }
    }

    @Test
    public void saveCombineDataTest() throws ValidationException {
        List<CombineData> data = new ArrayList<>();
        CombineData combine1 = CombineData.builder().serialNumber("A003").build();
        data.add(combine1);
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVehicleKey(combine1.getSerialNumber());
        vehicle1.setVehicleTelemetries(new ArrayList<>());
        when(vehicleDao.findById(combine1.getSerialNumber())).thenReturn(Optional.of(vehicle1));
        when(vehicleDao.save(vehicle1)).thenReturn(vehicle1);
        assertEquals(1, service.saveCombineData(data));
    }

    @Test
    public void saveCombineDataBadSecondDataTest() {
        List<CombineData> data = new ArrayList<>();
        CombineData combine1 = CombineData.builder().serialNumber("A003").build();
        data.add(combine1);
        CombineData combine2 = CombineData.builder().serialNumber("A004").build();
        data.add(combine2);
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVehicleKey(combine1.getSerialNumber());
        vehicle1.setVehicleTelemetries(new ArrayList<>());
        when(vehicleDao.findById(combine1.getSerialNumber())).thenReturn(Optional.of(vehicle1));
        try {
            service.saveCombineData(data);
            fail("Exception expected!");
        } catch (ValidationException e) {
            assertEquals("Data in single file should be only for one vehicle.", e.getMessage());
        }
    }

}
