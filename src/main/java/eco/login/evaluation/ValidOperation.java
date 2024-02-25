package eco.login.evaluation;

import eco.login.evaluation.validation.OperationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation used for validating the operation that is provided as input
 */
@Constraint(validatedBy = OperationValidator.class)
@Target({FIELD, METHOD, ANNOTATION_TYPE, TYPE_USE})
@Retention(RUNTIME)
@Documented
public @interface ValidOperation {
    String message() default "Specific operation is supported for specific types of fields";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
