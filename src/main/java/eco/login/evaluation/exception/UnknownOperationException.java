package eco.login.evaluation.exception;

public class UnknownOperationException extends Exception {
    public UnknownOperationException(String operationName) {
        super(String.format("Unknown operation %s. Re-check input data", operationName));
    }
}
