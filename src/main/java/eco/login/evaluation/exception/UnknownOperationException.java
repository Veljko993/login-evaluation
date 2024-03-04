package eco.login.evaluation.exception;

/**
 * Custom wrapper exception thrown with all errors occurred during the validation of the operation related input
 */
public class UnknownOperationException extends Exception {
    public UnknownOperationException(String operationName) {
        super(String.format("Unknown operation %s. Re-check input data", operationName));
    }
}
