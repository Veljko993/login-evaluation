package eco.login.evaluation.service;

import eco.login.evaluation.exception.ValidationException;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service for loading the data
 */
public interface DataLoaderService {

    /**
     * method for loading the CSV file into the system
     *
     * @param file - CSV file to be loaded into the system
     * @return - number of successfully loaded rows
     * @throws ValidationException - all validation issues
     */
    int loadCSVFile(MultipartFile file) throws ValidationException;
}
