package eco.login.evaluation.service;

import eco.login.evaluation.exception.ValidationException;
import eco.login.evaluation.model.CombineData;
import eco.login.evaluation.model.TractorData;

import java.util.List;

public interface TelemetryService {

    int saveTractorData(List<TractorData> parsedData) throws ValidationException;

    int saveCombineData(List<CombineData> parsedData) throws ValidationException;
}
