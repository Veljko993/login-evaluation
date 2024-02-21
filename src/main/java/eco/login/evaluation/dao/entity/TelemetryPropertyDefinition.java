package eco.login.evaluation.dao.entity;

import eco.login.evaluation.common.TelemetryPropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TLMTRY_PROP_DEFN")
@Data
@AllArgsConstructor
public class TelemetryPropertyDefinition implements Serializable {

    @Id @Column(name="TLMTRY_PROP_DEFN_KY") private Integer telemetryPropertyDefinitionKey;
    @Column(name="TLMTRY_PROP_NM") private String telemetryPropertyName;
    @Basic
    @Enumerated(EnumType.ORDINAL) @Column(name = "VHCL_TYPE_KY")
    private TelemetryPropertyType telemetryPropertyType;

}
