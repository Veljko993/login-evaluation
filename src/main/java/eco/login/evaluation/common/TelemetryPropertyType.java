package eco.login.evaluation.common;

import eco.login.evaluation.exception.UnsupportedValueException;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author vantonijevic
 * <p>
 * Enum describing property types, alongside the supported operations to be performed on those types of properties
 */
public enum TelemetryPropertyType implements Serializable {
    TEXT, DATE, INT, DOUBLE, BOOLEAN;
    //Add supported operations for each of the enums

    public void validateValue(String value) throws UnsupportedValueException {
        boolean valid = false;
        switch (this) {
            case INT -> {
                Integer.parseInt(value);
                valid = true;
            }
            case DOUBLE -> {
                Double.parseDouble(value);
                valid = true;
            }
            case DATE -> Timestamp.valueOf(value); // TODO Check this one
            case BOOLEAN -> Boolean.parseBoolean(value);
            case TEXT -> StringUtils.isEmpty(value);
            default -> throw new UnsupportedValueException(value);
        }
        if(!valid){
            throw new UnsupportedValueException(this, value);
        }
    }

}
