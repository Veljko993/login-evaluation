package eco.login.evaluation.common;

import eco.login.evaluation.exception.ValidationException;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static eco.login.evaluation.common.ParseUtil.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParseUtilTest {
    @Test
    public void parseIntPositiveCase() throws ValidationException {
        assertNull(parseInt(null));
        assertEquals(1, parseInt("1"));
        assertNull(parseInt(""));
        assertNull(parseInt("NA"));
    }

    @Test
    public void parseIntNegativeCase() {
        try {
            assertNull(parseInt("String"));
            fail("Exception expected!");
        } catch (ValidationException e) {
            assertInstanceOf(NumberFormatException.class, e.getCause());
        }
    }

    @Test
    public void parseDoublePositiveCase() throws ValidationException {
        assertNull(parseDouble(null));
        assertEquals(1.0, parseDouble("1.0"));
        assertNull(parseDouble(""));
        assertNull(parseDouble("NA"));
    }

    @Test
    public void parseDoubleNegativeCase() {
        try {
            assertNull(parseDouble("String"));
            fail("Exception expected!");
        } catch (ValidationException e) {
            assertInstanceOf(NumberFormatException.class, e.getCause());
        }
    }

    @Test
    public void parseBooleanPositiveCase() throws ValidationException {
        assertFalse(parseBoolean("false"));
        assertFalse(parseBoolean("0"));
        assertFalse(parseBoolean("inactive"));
        assertFalse(parseBoolean("off"));
        assertFalse(parseBoolean("na"));
        assertTrue(parseBoolean("true"));
        assertTrue(parseBoolean("1"));
        assertTrue(parseBoolean("active"));
        assertTrue(parseBoolean("on"));
    }

    @Test
    public void parseBooleanNegativeCase() {
        try {
            parseBoolean("Test");
            fail("Exception expected!");
        } catch (ValidationException e) {
            assertEquals("Unsupported value for boolean. Allowed values (on, off, active, inactive, 1, 0, true, false, na). Actual: test", e.getMessage());
        }
    }

    @Test
    public void parseTimestampPositiveCase() throws ValidationException, ParseException {
        long time = new SimpleDateFormat("MMM dd, yyyy, h:mm:ss a").parse("FEB 29, 2024, 10:22:13 AM").getTime();
        assertEquals(new Timestamp(time), parseTimestamp("FEB 29, 2024, 10:22:13 AM"));
    }

    @Test
    public void parseTimestampNegativeCase() {
        try {
            parseTimestamp("");
            fail("exception expected!");
        } catch (ValidationException e) {
            assertInstanceOf(ParseException.class, e.getCause());

        }
        try {
            parseTimestamp("2024-03-04");
            fail("exception expected!");
        } catch (ValidationException e) {
            assertInstanceOf(ParseException.class, e.getCause());

        }
        try {
            parseTimestamp("Test");
            fail("exception expected!");
        } catch (ValidationException e) {
            assertInstanceOf(ParseException.class, e.getCause());

        }
    }

}
