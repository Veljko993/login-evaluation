package eco.login.evaluation.service;

import eco.login.evaluation.model.CombineData;
import eco.login.evaluation.model.CombineDataCSV;
import eco.login.evaluation.model.TractorData;
import eco.login.evaluation.model.TractorDataCSV;

import java.util.List;

public interface ParseCSVService {

    List<TractorData> parseTractorData(List<TractorDataCSV> csvData);

    List<CombineData> parseCombineData(List<CombineDataCSV> csvData);
}
