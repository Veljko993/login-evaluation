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

    public static Operation parse(String operationName) throws UnknownOperationException {
        for (Operation op : Operation.values()) {
            if (op.getName().equalsIgnoreCase(operationName)) {
                return op;
            }
        }
        throw new UnknownOperationException(operationName);
    }

    public boolean isSupported(TelemetryPropertyType type) {
        return supportedOperations.contains(type);
    }
}
