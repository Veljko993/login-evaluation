package eco.login.evaluation.service;

import eco.login.evaluation.model.Filter;

import java.util.List;

public interface DataReadingService {
    String getVehicleData(List<Filter> validFilters);
}
