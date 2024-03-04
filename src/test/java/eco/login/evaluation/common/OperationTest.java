package eco.login.evaluation.common;

import eco.login.evaluation.exception.UnknownOperationException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static eco.login.evaluation.common.TelemetryPropertyType.*;
import static org.junit.jupiter.api.Assertions.*;

public class OperationTest {
    @Test
    public void testOperationName() {
        assertEquals("equals", Operation.EQUALS.getName());
        assertEquals("contains", Operation.CONTAINS.getName());
        assertEquals("lessthan", Operation.LESS_THAN.getName());
        assertEquals("greaterthan", Operation.GREATER_THAN.getName());
    }

    @Test
    public void testSupportedOperations() {
        assertEquals(Arrays.asList(TEXT, DATE, INT, DOUBLE, BOOLEAN), Operation.EQUALS.getSupportedOperations());
        assertEquals(Arrays.asList(TEXT), Operation.CONTAINS.getSupportedOperations());
        assertEquals(Arrays.asList(DOUBLE, INT, DATE), Operation.LESS_THAN.getSupportedOperations());
        assertEquals(Arrays.asList(DOUBLE, INT, DATE), Operation.GREATER_THAN.getSupportedOperations());
    }

    @Test
    public void testParse() throws UnknownOperationException {
        assertEquals(Operation.EQUALS, Operation.parse(null));
        assertEquals(Operation.EQUALS, Operation.parse("EquAls"));
        assertEquals(Operation.CONTAINS, Operation.parse("CONTAINS"));
        assertEquals(Operation.LESS_THAN, Operation.parse("LESSthan"));
        assertEquals(Operation.GREATER_THAN, Operation.parse("greaterTHAN"));
        try {
            Operation.parse("dummyValue");
            fail("Exception epxected!");
        } catch (UnknownOperationException e) {
            assertEquals("Unknown operation dummyValue. Re-check input data", e.getMessage());
        }
    }

    @Test
    public void testIsSupported() {
        assertTrue(Operation.EQUALS.isSupported(DATE));
        assertFalse(Operation.CONTAINS.isSupported(DATE));
    }
}
