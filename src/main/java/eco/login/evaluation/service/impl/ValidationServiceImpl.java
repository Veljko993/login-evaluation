package eco.login.evaluation.service.impl;

import eco.login.evaluation.common.Operation;
import eco.login.evaluation.common.TelemetryPropertyType;
import eco.login.evaluation.exception.UnknownOperationException;
import eco.login.evaluation.exception.ValidationException;
import eco.login.evaluation.model.Filter;
import eco.login.evaluation.service.PropertyDefinitionService;
import eco.login.evaluation.service.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ValidationServiceImpl implements ValidationService {

    private final PropertyDefinitionService definitionService;

    public ValidationServiceImpl(PropertyDefinitionService definitionService) {
        this.definitionService = definitionService;
    }

    @Override
    public void validateFile(MultipartFile file) throws ValidationException {
        if (!Objects.requireNonNull(file.getOriginalFilename()).toLowerCase().endsWith(".csv")) {
            throw new ValidationException("Only CSV files are allowed.", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Filter> validateFilters(Filter[] filters) throws ValidationException {
        if (filters == null || filters.length == 0) {
            throw new ValidationException("No filter provided.", HttpStatus.BAD_REQUEST);
        }
        List<Filter> validFilters = new ArrayList<>();
        for (Filter filter : filters) {
            if(validateFilter(filter)){
                validFilters.add(filter);
            }
        }
        return validFilters;
    }

    private boolean validateFilter(Filter filter) {
        boolean valid = false;
        TelemetryPropertyType propertyType = definitionService.getPropertyType(filter.getField());
        if (propertyType == null) {
            log.error("Validation error: Field doesn't exist. Value: " + filter.getField());
        } else {
            valid = validateOperation(filter, propertyType);
        }
        return valid;
    }

    private static boolean validateOperation(Filter filter, TelemetryPropertyType propertyType) {
        boolean valid = true;
        try {
            Operation operation = Operation.parse(filter.getOperation());
            if (operation != Operation.EQUALS && !operation.isSupported(propertyType)) {
                log.error("Validation error: Operation is not supported for the provided field. Field: {}, operation: {}", filter.getField(), filter.getOperation());
                valid = false;
            }
        } catch (UnknownOperationException e) {
            log.error("Validation error: Operation invalid. Value: " + filter.getOperation());
            valid = false;
        }
        return valid;
    }
}
