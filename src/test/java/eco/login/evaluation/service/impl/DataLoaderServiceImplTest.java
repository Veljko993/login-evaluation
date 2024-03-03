package eco.login.evaluation.service.impl;

import eco.login.evaluation.dao.repository.VehicleDao;
import eco.login.evaluation.exception.FileReadingException;
import eco.login.evaluation.exception.ValidationException;
import eco.login.evaluation.model.CombineData;
import eco.login.evaluation.model.CombineDataCSV;
import eco.login.evaluation.model.TractorData;
import eco.login.evaluation.model.TractorDataCSV;
import eco.login.evaluation.service.DataLoaderService;
import eco.login.evaluation.service.ParseCSVService;
import eco.login.evaluation.service.PropertyDefinitionService;
import eco.login.evaluation.service.TelemetryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DataLoaderServiceImplTest {

    @Mock
    private ParseCSVService parseService;
    @Mock
    private TelemetryService telemetryService;

    private DataLoaderService service;

    private MultipartFile file;
    @BeforeEach
    public void setUp() {
        service = new DataLoaderServiceImpl(parseService, telemetryService);
    }

    @Test
    public void testLoadingFile() throws FileReadingException, ValidationException {
        file = new MockMultipartFile("tractorData.csv", "LD_Atractor.csv", null, new byte[0]);
        List<TractorDataCSV> csvData = new ArrayList<>();
        when(parseService.parseTractorCSV(file)).thenReturn(csvData);
        List<TractorData> parsedData = new ArrayList<>();
        when(parseService.parseTractorData(csvData)).thenReturn(parsedData);
        when(telemetryService.saveTractorData(parsedData)).thenReturn(2);
        assertEquals(2, service.loadCSVFile(file));
    }

    @Test
    public void testLoadingCobineFile() throws FileReadingException, ValidationException {
        file = new MockMultipartFile("combineData.csv", "LD_Combine.csv", null, new byte[0]);
        List<CombineDataCSV> csvData = new ArrayList<>();
        when(parseService.parseCombineCSV(file)).thenReturn(csvData);
        List<CombineData> parsedData = new ArrayList<>();
        when(parseService.parseCombineData(csvData)).thenReturn(parsedData);
        when(telemetryService.saveCombineData(parsedData)).thenReturn(2);
        assertEquals(2, service.loadCSVFile(file));
    }

    @Test
    public void testLoadingInvalidFile() {
        file = new MockMultipartFile("combineData.csv", "LD_Bombine.csv", null, new byte[0]);
        try {
            service.loadCSVFile(file);
        } catch (ValidationException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
        }
    }

    @Test
    public void testLoadingFileReadingException() throws FileReadingException {
        file = new MockMultipartFile("combineData.csv", "LD_Combine.csv", null, new byte[0]);
        when(parseService.parseCombineCSV(file)).thenThrow(new FileReadingException("Oops, something went wrong", null));
        try {
            service.loadCSVFile(file);
        } catch (ValidationException e) {
            assertInstanceOf(FileReadingException.class, e.getCause());
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
        }
    }





}
