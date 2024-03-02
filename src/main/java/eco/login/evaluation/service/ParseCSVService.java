package eco.login.evaluation.service;

import eco.login.evaluation.exception.FileReadingException;
import eco.login.evaluation.model.CombineData;
import eco.login.evaluation.model.CombineDataCSV;
import eco.login.evaluation.model.TractorData;
import eco.login.evaluation.model.TractorDataCSV;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Service for parsing the CSV files and other CSV data
 */
public interface ParseCSVService {

    /**
     * Method for parsing TractorDataCSV into TractorData
     *
     * @param csvData
     * @return converted data, all invalid data is removed at this point
     */
    List<TractorData> parseTractorData(List<TractorDataCSV> csvData);

    /**
     * Method for parsing CombineDataCSV into CombineData
     *
     * @param csvData
     * @return converted data, all invalid data is removed at this point
     */
    List<CombineData> parseCombineData(List<CombineDataCSV> csvData);

    /**
     * Method for reading CSV file, and parsing it into TractorDataCSV objects
     *
     * @param file - csv file
     * @return list of parsed objects
     * @throws FileReadingException
     */
    List<TractorDataCSV> parseTractorCSV(MultipartFile file) throws FileReadingException;

    /**
     * Method for reading CSV file, and parsing it into CombineDataCSV objects
     *
     * @param file - csv file
     * @return list of parsed objects
     * @throws FileReadingException
     */
    List<CombineDataCSV> parseCombineCSV(MultipartFile file) throws FileReadingException;
}
