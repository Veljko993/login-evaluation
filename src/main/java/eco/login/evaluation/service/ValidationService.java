package eco.login.evaluation.service;

import eco.login.evaluation.exception.ValidationException;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service used for validating the input file. CSV file, input parameters
 */
public interface ValidationService {
    /**
     * Validate the input file
     *
     * @param file
     * @throws ValidationException
     */
    void validateFile(MultipartFile file) throws ValidationException;

}
