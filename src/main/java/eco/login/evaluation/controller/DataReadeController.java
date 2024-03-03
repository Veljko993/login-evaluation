package eco.login.evaluation.controller;

import eco.login.evaluation.exception.ValidationException;
import eco.login.evaluation.model.Filter;
import eco.login.evaluation.service.DataReadingService;
import eco.login.evaluation.service.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class DataReadeController {
    private final ValidationService validationService;
    private final DataReadingService readingService;

    public DataReadeController(ValidationService validationService, DataReadingService readingService) {
        this.validationService = validationService;
        this.readingService = readingService;
    }

    @PostMapping("/filter")
    public ResponseEntity<String> filterData(@RequestBody Filter[] filters) throws ValidationException {
        List<Filter> validFilters = validationService.validateFilters(filters);
        String response = readingService.getVehicleData(validFilters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
