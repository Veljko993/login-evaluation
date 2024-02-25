package eco.login.evaluation;

import eco.login.evaluation.dao.entity.TelemetryPropertyDefinition;
import eco.login.evaluation.dao.repository.TelemetryPropertyDefinitionDao;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PropertyDefinitionService {
    private final TelemetryPropertyDefinitionDao propertyDefinitionDao;
    private final List<TelemetryPropertyDefinition> propertyDefinitionList;

    public PropertyDefinitionService(TelemetryPropertyDefinitionDao propertyDefinitionDao) {
        this.propertyDefinitionDao = propertyDefinitionDao;
        propertyDefinitionList = new ArrayList<>();
    }

    @PostConstruct
    public void readDataFromDatabase() {
        propertyDefinitionDao.findAll().forEach(propertyDefinitionList::add);
    }

    public TelemetryPropertyDefinition validateProperty(String field, String value) {
        Optional<TelemetryPropertyDefinition> prop = propertyDefinitionList.parallelStream().filter(propDefn -> {
            propDefn.getTelemetryPropertyName().equalsIgnoreCase(field);
            propDefn.getTelemetryPropertyType()
        }).findFirst();

        return null;
    }

}
