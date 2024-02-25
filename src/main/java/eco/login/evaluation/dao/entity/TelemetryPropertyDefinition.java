package eco.login.evaluation.dao.entity;

import eco.login.evaluation.common.TelemetryPropertyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author vantonijevic
 * <p>
 * Entity used for storing definition of all *allowed* properties
 */
@Entity
@Table(name = "TLMTRY_PROP_DEFN")
@Data
@AllArgsConstructor
public class TelemetryPropertyDefinition implements Serializable {

    @Id
    @Column(name = "TLMTRY_PROP_DEFN_KY")
    private Integer telemetryPropertyDefinitionKey;
    @Column(name = "TLMTRY_PROP_NM")
    private String telemetryPropertyName;
    @Basic
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TLMTRY_PROP_TYPE_KY")
    private TelemetryPropertyType telemetryPropertyType;

}
