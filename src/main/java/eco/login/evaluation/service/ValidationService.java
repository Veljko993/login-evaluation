package eco.login.evaluation.service;

import eco.login.evaluation.exception.ValidationException;
import eco.login.evaluation.model.Filter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * Validate the input filters used for filtering the data
     *
     * @param filters - an array of raw {@link Filter} objects
     * @return
     */
    List<Filter> validateFilters(Filter[] filters) throws ValidationException;
}
