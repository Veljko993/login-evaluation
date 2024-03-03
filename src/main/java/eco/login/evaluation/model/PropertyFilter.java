package eco.login.evaluation.model;

import eco.login.evaluation.common.Operation;
import eco.login.evaluation.common.TelemetryPropertyType;
import eco.login.evaluation.dao.entity.TelemetryPropertyDefinition;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Builder
@Data
@Slf4j
public class PropertyFilter {
    private Integer propertyKy;
    private String propertyName;
    private TelemetryPropertyType propertyType;
    private Operation operation;
    private String filterValue;

    public static PropertyFilter copy(TelemetryPropertyDefinition def) {
        return PropertyFilter.builder().propertyKy(def.getTelemetryPropertyDefinitionKey()).propertyType(def.getTelemetryPropertyType()).propertyName(def.getTelemetryPropertyName()).build();
    }
}
