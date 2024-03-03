package eco.login.evaluation.service;

import eco.login.evaluation.model.Filter;
import org.json.JSONObject;

import java.util.List;

public interface DataReadingService {
    String getVehicleData(List<Filter> validFilters);
}
