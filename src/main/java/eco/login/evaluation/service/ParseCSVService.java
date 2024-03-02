package eco.login.evaluation.service;

import eco.login.evaluation.exception.FileReadingException;
import eco.login.evaluation.model.CombineData;
import eco.login.evaluation.model.CombineDataCSV;
import eco.login.evaluation.model.TractorData;
import eco.login.evaluation.model.TractorDataCSV;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ParseCSVService {

    List<TractorData> parseTractorData(List<TractorDataCSV> csvData);

    List<CombineData> parseCombineData(List<CombineDataCSV> csvData);

    List<TractorDataCSV> parseTractorCSV(MultipartFile file) throws FileReadingException;

    List<CombineDataCSV> parseCombineCSV(MultipartFile file) throws FileReadingException;
}
