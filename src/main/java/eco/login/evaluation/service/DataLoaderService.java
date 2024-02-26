package eco.login.evaluation.service;

import eco.login.evaluation.exception.ValidationException;
import org.springframework.web.multipart.MultipartFile;

public interface DataLoaderService {

    int loadCSVFile(MultipartFile file) throws ValidationException;
}
