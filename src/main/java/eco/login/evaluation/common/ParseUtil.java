package eco.login.evaluation.common;

import eco.login.evaluation.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Slf4j
public class ParseUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    public static Integer parseInt(String text) throws ValidationException {
        log.debug("Parsing integer: " + text);
        try {
            return (text.isBlank() || text.equalsIgnoreCase("NA")) ? null : Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new ValidationException("Not an integer. Recieved text: " + text, e);
        }
    }

    public static Boolean parseBoolean(String text) throws ValidationException {
        boolean result;
        text = text.toLowerCase();
        if (text.equals("off") || text.equals("inactive") || text.equals("0") || text.equals("false")) {
            log.debug("Parsing: " + text + " to false");
            result = Boolean.FALSE;
        } else if (text.equals("on") || text.equals("active") || text.equals("1") || text.equals("true")) {
            log.debug("Parsing: " + text + " to true");

            result = Boolean.TRUE;
        } else {
            throw new ValidationException("Unsupported value for boolean. Allowed values (on, off, actve, inactive, 1, 0, true, false). Actual: " + text);
        }
        return result;
    }

    public static Double parseDouble(String text) throws ValidationException {
        log.debug("Parsing double: " + text);
        try {
            return (text.isBlank() || text.equalsIgnoreCase("NA")) ? null : Double.parseDouble(text);
        } catch (NumberFormatException e) {
            throw new ValidationException("Not a double. Recieved text: " + text, e);

        }

    }

    public static Timestamp parseTimestamp(String text) throws ValidationException {
        log.debug("Parsing timestamp: " + text);
        try {
            return new Timestamp(dateFormat.parse(text).getTime());
        } catch (ParseException e) {
            throw new ValidationException("Text is not in correct dateFormat (expected: MM/dd/yyyy HH:mm), actual: " + text,
                    e);
        }
    }
}
