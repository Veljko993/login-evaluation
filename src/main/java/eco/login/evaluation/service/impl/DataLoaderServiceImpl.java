package eco.login.evaluation.service.impl;

import eco.login.evaluation.exception.FileReadingException;
import eco.login.evaluation.exception.ValidationException;
import eco.login.evaluation.model.CombineData;
import eco.login.evaluation.model.CombineDataCSV;
import eco.login.evaluation.model.TractorData;
import eco.login.evaluation.model.TractorDataCSV;
import eco.login.evaluation.service.DataLoaderService;
import eco.login.evaluation.service.ParseCSVService;
import eco.login.evaluation.service.TelemetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class DataLoaderServiceImpl implements DataLoaderService {

    private final ParseCSVService parseService;

    private final TelemetryService telemetryService;

    @Autowired
    public DataLoaderServiceImpl(ParseCSVService parseService, TelemetryService telemetryService) {
        this.parseService = parseService;
        this.telemetryService = telemetryService;
    }


    @Override
    public int loadCSVFile(MultipartFile file) throws ValidationException {
        int loadedRows = 0;
        try {
            if (file.getOriginalFilename().startsWith("LD_C")) {
                loadedRows = processCombineData(file);
            } else if (file.getOriginalFilename().startsWith("LD_A")) {
                loadedRows = processTractorData(file);
            } else {
                throw new ValidationException("Unsupported file name. It should start with either LD_C or LD_A", HttpStatus.BAD_REQUEST);
            }
        } catch (FileReadingException e) {
            throw new RuntimeException(e);
        }
        return loadedRows;
    }

    private int processTractorData(MultipartFile file) throws FileReadingException, ValidationException {
        List<TractorDataCSV> csvData = parseService.parseTractorCSV(file);
        List<TractorData> parsedData = parseService.parseTractorData(csvData);
        return telemetryService.saveTractorData(parsedData);
    }

    private int processCombineData(MultipartFile file) throws FileReadingException, ValidationException {
        List<CombineDataCSV> csvData = parseService.parseCombineCSV(file);
        List<CombineData> parsedData = parseService.parseCombineData(csvData);
        return telemetryService.saveCombineData(parsedData);
    }

}
