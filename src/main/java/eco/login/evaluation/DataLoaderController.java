package eco.login.evaluation;

import eco.login.evaluation.exception.ValidationException;
import eco.login.evaluation.service.DataLoaderService;
import eco.login.evaluation.service.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for loading the data into the system
 */
@Slf4j
@RestController
public class DataLoaderController {

    private final ValidationService validationService;
    private final DataLoaderService dataLoaderService;

    public DataLoaderController(ValidationService validationService, DataLoaderService dataLoaderService) {
        this.validationService = validationService;
        this.dataLoaderService = dataLoaderService;
    }

    /**
     * Endpoint for uploading the CSV file into the system. It needs to satisfy specific requirements before it can be loaded successfully.
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            log.info("Performing validation of the file: " + file.getOriginalFilename());
            validationService.validateFile(file);
            log.debug("Validation successful.");
            log.info("Initiating loading of the data.");
            int loadedRows = dataLoaderService.loadCSVFile(file);
            log.debug(String.format("Loaded %s rows.", loadedRows));
            return ResponseEntity.ok(String.format("File uploaded successfully! %s row(s) entered in the system.", loadedRows));
        } catch (ValidationException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(e.getStatus())
                    .body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
