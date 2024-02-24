package eco.login.evaluation.common;

import java.io.Serializable;

/**
 * @author vantonijevic
 * <p>
 * Enum describing property types, alongside the supported operations to be performed on those types of properties
 */
public enum TelemetryPropertyType implements Serializable {
    TEXT, DATE, INT, DOUBLE, BOOLEAN
    //Add supported operations for each of the enums
}
