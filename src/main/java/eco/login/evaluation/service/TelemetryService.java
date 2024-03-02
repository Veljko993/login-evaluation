package eco.login.evaluation.service;

import eco.login.evaluation.exception.ValidationException;
import eco.login.evaluation.model.CombineData;
import eco.login.evaluation.model.TractorData;

import java.util.List;

/**
 * Service for processing telemetry data
 */
public interface TelemetryService {

    /**
     * Processing Tractor telemetry data
     * @param parsedData
     * @return number of successfully added rows
     * @throws ValidationException
     */
    int saveTractorData(List<TractorData> parsedData) throws ValidationException;
    /**
     * Processing Combine telemetry data
     * @param parsedData
     * @return number of successfully added rows
     * @throws ValidationException
     */
    int saveCombineData(List<CombineData> parsedData) throws ValidationException;
}
