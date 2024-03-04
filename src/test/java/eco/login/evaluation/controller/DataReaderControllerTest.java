package eco.login.evaluation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eco.login.evaluation.common.Operation;
import eco.login.evaluation.common.VehicleType;
import eco.login.evaluation.dao.entity.TelemetryProperty;
import eco.login.evaluation.dao.entity.TelemetryPropertyDefinition;
import eco.login.evaluation.dao.entity.Vehicle;
import eco.login.evaluation.dao.entity.VehicleTelemetry;
import eco.login.evaluation.dao.repository.TelemetryPropertyDefinitionDao;
import eco.login.evaluation.dao.repository.VehicleDao;
import eco.login.evaluation.dao.repository.VehicleDataDao;
import eco.login.evaluation.model.Filter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static eco.login.evaluation.common.PropertyNamesConst.SERIAL_NUMBER;
import static eco.login.evaluation.common.PropertyNamesConst.TOTAL_WORKING_HOURS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
public class DataReaderControllerTest {


    public JacksonTester<Filter[]> jsonTester;
    @Autowired
    protected WebApplicationContext wac;
    @MockBean
    VehicleDataDao vehicleDataDao;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private TelemetryPropertyDefinitionDao definitionDao;

    @BeforeEach
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
        JacksonTester.initFields(this, objectMapper);
        initializeData();
        List list = Arrays.asList(1L, 2L);
        when(vehicleDataDao.executeNativeQuery(any())).thenReturn(list);
    }

    private void initializeData() {
        List<VehicleTelemetry> telemetries = new ArrayList<>();
        Vehicle vehicle = new Vehicle("A003", VehicleType.TRACTOR, telemetries);
        List<TelemetryProperty> telemProps = new ArrayList<>();
        TelemetryPropertyDefinition serialNumberDefn = definitionDao.findById(2L).orElse(null);
        TelemetryPropertyDefinition totWorkHrsDefn = definitionDao.findById(6L).orElse(null);
        TelemetryProperty telemProp = TelemetryProperty.builder().telemetryPropertyKey(1L).telemetryPropertyDefinition(serialNumberDefn).telemetryPropertyValueText("A003").build();
        telemProps.add(telemProp);
        TelemetryProperty telemProp1 = TelemetryProperty.builder().telemetryPropertyKey(2L).telemetryPropertyDefinition(totWorkHrsDefn).telemetryPropertyValueDecimal(1185.36).build();
        telemProps.add(telemProp1);
        VehicleTelemetry vehTelem = new VehicleTelemetry(1L, vehicle, telemProps);
        telemetries.add(vehTelem);
        vehicleDao.save(vehicle);

        List<VehicleTelemetry> telemetriesComb = new ArrayList<>();
        Vehicle vehicleComb = new Vehicle("A004", VehicleType.COMBINE, telemetriesComb);
        List<TelemetryProperty> telemCombProps = new ArrayList<>();
        TelemetryProperty telemCombProp = TelemetryProperty.builder().telemetryPropertyKey(1L).telemetryPropertyDefinition(serialNumberDefn).telemetryPropertyValueText("A004").build();
        telemCombProps.add(telemCombProp);
        TelemetryProperty telemCombProp1 = TelemetryProperty.builder().telemetryPropertyKey(2L).telemetryPropertyDefinition(totWorkHrsDefn).telemetryPropertyValueDecimal(12.34).build();
        telemCombProps.add(telemCombProp1);
        VehicleTelemetry vehCombTelem = new VehicleTelemetry(1L, vehicleComb, telemCombProps);
        telemetries.add(vehCombTelem);

        vehicleDao.save(vehicleComb);
    }

    @Test
    public void testFilterEndpoint() throws Exception {
        Filter[] filters = new Filter[2];
        filters[0] = new Filter(SERIAL_NUMBER, "A003", Operation.EQUALS.getName());
        filters[1] = new Filter(TOTAL_WORKING_HOURS, "1185.36", null);
        String jsonString = jsonTester.write(filters).getJson();
        MvcResult result = mockMvc.perform(post("/filter").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andReturn();
        //TODO Check DB data, and tweak properties data preparation.
        assertEquals("[{\"serialNumber\":\"A003\",\"vehicleType\":\"TRACTOR\",\"properties\":{}}]", result.getResponse().getContentAsString());
    }


}
