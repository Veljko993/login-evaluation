package eco.login.evaluation.common;

import eco.login.evaluation.exception.UnknownOperationException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static eco.login.evaluation.common.TelemetryPropertyType.*;

/**
 * Enum used for storing supported operations, and validating which field supports which operation.
 *
 * @author vantonijevic
 */
@Getter
public enum Operation {
    EQUALS("equals", TEXT, DATE, INT, DOUBLE, BOOLEAN), //
    LESS_THAN("lessthan", DOUBLE, INT, DATE), //
    GREATER_THAN("greaterthan", DOUBLE, INT, DATE), //
    CONTAINS("contains", TEXT);

    private final String name;
    private final List<TelemetryPropertyType> supportedOperations;

    Operation(String name, TelemetryPropertyType... supportedOperations) {
        this.supportedOperations = Arrays.asList(supportedOperations);
        this.name = name;
    }

    /**
     * Parses the String into Operation ignoring the case of the operation name provided, default value is EQUALS.
     *
     * @param operationName
     * @return
     * @throws UnknownOperationException
     */
    public static Operation parse(String operationName) throws UnknownOperationException {
        if (operationName == null) {
            return EQUALS;
        }
        String opName = operationName.toLowerCase();
        for (Operation op : Operation.values()) {
            if (op.getName().equals(opName)) {
                return op;
            }
        }
        throw new UnknownOperationException(operationName);
    }

    public boolean isSupported(TelemetryPropertyType type) {
        return supportedOperations.contains(type);
    }
}
