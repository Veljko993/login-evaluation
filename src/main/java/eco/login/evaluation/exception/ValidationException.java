package eco.login.evaluation.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Custom wrapper exception thrown with all errors occurred during the input validation
 */
@Getter
public class ValidationException extends Exception {
    private final HttpStatus status;

    public ValidationException(String message, HttpStatus status) {
        this(message, status, null);
    }

    public ValidationException(String message, Throwable e) {
        this(message, null, e);
    }

    public ValidationException(String message, HttpStatus status, Throwable e) {
        super(message, e);
        this.status = status;
    }

    public ValidationException(String message) {
        this(message, null, null);
    }
}
