package eco.login.evaluation.service.impl;

import eco.login.evaluation.model.CombineData;
import eco.login.evaluation.model.CombineDataCSV;
import eco.login.evaluation.model.TractorData;
import eco.login.evaluation.model.TractorDataCSV;
import eco.login.evaluation.service.ParseCSVService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ParseCSVServiceImpl implements ParseCSVService {
    @Override
    public List<TractorData> parseTractorData(List<TractorDataCSV> csvData) {
        return csvData.parallelStream().map(TractorData::convert).collect(Collectors.toList());
    }

    @Override
    public List<CombineData> parseCombineData(List<CombineDataCSV> csvData) {
        return csvData.parallelStream().map(CombineData::convert).collect(Collectors.toList());
    }


}
