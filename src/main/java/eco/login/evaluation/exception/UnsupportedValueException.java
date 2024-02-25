package eco.login.evaluation.exception;

import eco.login.evaluation.common.TelemetryPropertyType;

public class UnsupportedValueException extends Throwable {
    public UnsupportedValueException(String value) {
    }

    public UnsupportedValueException(TelemetryPropertyType telemetryPropertyType, String value) {
    }
}
