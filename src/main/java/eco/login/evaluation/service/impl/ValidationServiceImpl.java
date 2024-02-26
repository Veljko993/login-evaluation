package eco.login.evaluation.service.impl;

import eco.login.evaluation.exception.ValidationException;
import eco.login.evaluation.service.ValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public void validateFile(MultipartFile file) throws ValidationException {
        if (!Objects.requireNonNull(file.getOriginalFilename()).toLowerCase().endsWith(".csv")) {
            throw new ValidationException("Only CSV files are allowed.", HttpStatus.BAD_REQUEST);
        }
    }
}
