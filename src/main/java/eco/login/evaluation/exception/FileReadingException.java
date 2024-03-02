package eco.login.evaluation.exception;

/**
 * Custom wrapper exception thrown with all errors occurred during the reading of the file
 */
public class FileReadingException extends Exception {
    public FileReadingException(String message, Exception e) {
        super(message, e);
    }
}
