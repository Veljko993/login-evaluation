package eco.login.evaluation.service.impl;

import eco.login.evaluation.common.TelemetryPropertyType;
import eco.login.evaluation.exception.ValidationException;
import eco.login.evaluation.model.Filter;
import eco.login.evaluation.service.PropertyDefinitionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidationServiceImplTest {

    @Mock
    private PropertyDefinitionService definitionService;
    private ValidationServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new ValidationServiceImpl(definitionService);
    }


    @Test
    public void validateFileCorrectNameTest() throws ValidationException {
        MultipartFile file = new MockMultipartFile("correct.csv", "correct.csv", null, new byte[1]);
        service.validateFile(file);
    }

    @Test
    public void validateFileNullTest() {
        MultipartFile file = new MockMultipartFile("badfile.csv", null, null, new byte[1]);
        try {
            service.validateFile(file);
            fail("Exception expected!");
        } catch (ValidationException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
        }
    }

    @Test
    public void validateFileTextTest() {
        MultipartFile file = new MockMultipartFile("incorrect.txt", "incorrect.txt", null, new byte[1]);
        try {
            service.validateFile(file);
            fail("Exception expected!");
        } catch (ValidationException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
        }
    }

    @Test
    public void validateFilterParamValidation() {
        try {
            service.validateFilters(null);
            fail("Exception expected!");
        } catch (ValidationException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
        }

        try {
            service.validateFilters(new Filter[0]);
            fail("Exception expected!");
        } catch (ValidationException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
        }
    }

    @Test
    public void testFiltersPositive() throws ValidationException {
        Filter[] filters = new Filter[2];
        Filter filter1 = new Filter("SerialNumber", "A003", "Contains");
        Filter filter2 = new Filter("TotalWorkingHours", "1185.36", null);
        filters[0] = filter1;
        filters[1] = filter2;
        when(definitionService.getPropertyType(filter1.getField())).thenReturn(TelemetryPropertyType.TEXT);
        when(definitionService.getPropertyType(filter2.getField())).thenReturn(TelemetryPropertyType.DOUBLE);
        List<Filter> validatedFilters = service.validateFilters(filters);
        assertEquals(2, validatedFilters.size());
    }

    @Test
    public void testFiltersBadOperation() throws ValidationException {
        Filter[] filters = new Filter[2];
        Filter filter1 = new Filter("SerialNumber", "A003", "GreaterThan");
        Filter filter2 = new Filter("TotalWorkingHours", "1185.36", "StartsWith");
        filters[0] = filter1;
        filters[1] = filter2;
        when(definitionService.getPropertyType(filter1.getField())).thenReturn(TelemetryPropertyType.TEXT);
        when(definitionService.getPropertyType(filter2.getField())).thenReturn(TelemetryPropertyType.DOUBLE);
        List<Filter> validatedFilters = service.validateFilters(filters);
        assertEquals(0, validatedFilters.size());
    }

    @Test
    public void testFiltersBadField() throws ValidationException {
        Filter[] filters = new Filter[2];
        Filter filter1 = new Filter("SerialNumber", "A003", null);
        Filter filter2 = new Filter("TotalWorkingHrs", "1185.36", null);
        filters[0] = filter1;
        filters[1] = filter2;
        when(definitionService.getPropertyType(filter1.getField())).thenReturn(TelemetryPropertyType.TEXT);
        when(definitionService.getPropertyType(filter2.getField())).thenReturn(null);
        List<Filter> validatedFilters = service.validateFilters(filters);
        assertEquals(1, validatedFilters.size());
    }
}
