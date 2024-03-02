package eco.login.evaluation.common;

import eco.login.evaluation.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Utility class for parsing objects
 *
 * @author vantonijevic
 */
@Slf4j
public class ParseUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy, h:mm:ss a");

    /**
     * Method for parsing String to Integer
     *
     * @param text - input string
     * @return parssed Integer or null in case of empty string or NA string
     * @throws ValidationException in case that number is not a number
     */
    public static Integer parseInt(String text) throws ValidationException {
        log.debug("Parsing integer: " + text);
        try {
            return (StringUtils.isBlank(text) || text.equalsIgnoreCase("NA")) ? null : Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new ValidationException("Not an integer. Recieved text: " + text, e);
        }
    }

    /**
     * Method for parsing boolean
     *
     * @param text - input string
     * @return - true if value is (on, active, 1, or true), false if value is (off, inactive, 0, false or na)
     * @throws ValidationException in case that input doesn't match any of the aforementioned values
     */
    public static Boolean parseBoolean(String text) throws ValidationException {
        boolean result;
        text = text.toLowerCase();
        if (text.equals("off") || text.equals("inactive") || text.equals("0") || text.equals("false") || text.equals("na")) {
            log.debug("Parsing: " + text + " to false");
            result = Boolean.FALSE;
        } else if (text.equals("on") || text.equals("active") || text.equals("1") || text.equals("true")) {
            log.debug("Parsing: " + text + " to true");
            result = Boolean.TRUE;
        } else {
            throw new ValidationException("Unsupported value for boolean. Allowed values (on, off, actve, inactive, 1, 0, true, false, na). Actual: " + text);
        }
        return result;
    }

    /**
     * Method for parsing double
     *
     * @param text - input text
     * @return parsed double or null in case that input is blank or NA
     * @throws ValidationException in case that parsing double fails
     */
    public static Double parseDouble(String text) throws ValidationException {
        log.debug("Parsing double: " + text);
        try {
            return (StringUtils.isBlank(text) || text.equalsIgnoreCase("NA")) ? null : Double.parseDouble(text);
        } catch (NumberFormatException e) {
            throw new ValidationException("Not a double. Recieved text: " + text, e);

        }

    }

    /**
     * Mathod for parsing timestamp to date
     *
     * @param text - input text
     * @return Timestamp if the timestamp is in correct format (MMM dd, yyyy, h:mm:ss a)
     * @throws ValidationException if parsing fails
     */
    public static Timestamp parseTimestamp(String text) throws ValidationException {
        log.debug("Parsing timestamp: " + text);
        try {
            return new Timestamp(dateFormat.parse(text).getTime());
        } catch (ParseException | NumberFormatException e) {
            throw new ValidationException("Text is not in correct dateFormat (expected: MMM dd, yyyy, h:mm:ss a), actual: " + text,
                    e);
        }
    }
}
