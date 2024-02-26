package eco.login.evaluation.validation;

import eco.login.evaluation.FilterDataDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OperationValidator implements ConstraintValidator<ValidOperation, FilterDataDto> {
    @Override
    public boolean isValid(FilterDataDto data, ConstraintValidatorContext constraintValidatorContext) {
        //Parse data to a operation type
        //Check that all
        return false;
    }
}
