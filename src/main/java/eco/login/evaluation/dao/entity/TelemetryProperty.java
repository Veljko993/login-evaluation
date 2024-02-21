package eco.login.evaluation.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TLMTRY_PROP")
@Data
@AllArgsConstructor
@Builder
public class TelemetryProperty implements Serializable {
    @Id
    @SequenceGenerator(name = "TLMTRY_PROP_KEY_GENERATOR", sequenceName = "SEQ_TLMTRY_PROP", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TLMTRY_PROP_KEY_GENERATOR")
    @Column(name = "TLMTRY_PROP_KY")
    private Long telemetryPropertyKey;

    @ManyToOne
    @JoinColumn(name = "TLMTRY_PROP_DEFN_KY")
    private TelemetryPropertyDefinition telemetryPropertyDefinition;

    @Column(name = "TLMTRY_PROP_ORD_NR")
    private Integer telemetryPropertyOrderNumber;

    @Column(name = "TLMTRY_PROP_VALU_DCML")
    private Double telemetryPropertyValueDecimal;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TLMTRY_PROP_VALU_TS")
    private Date telemetryPropertyValueDate;

    @Column(name = "TLMTRY_PROP_VALU_FG")
    private Boolean telemetryPropertyValueFlag;

    @Column(name = "TLMTRY_PROP_VALU_INTG")
    private Integer telemetryPropertyValueInteger;

    @Column(name = "TLMTRY_PROP_VALU_TX")
    private String telemetryPropertyValueText;

    // bidirectional many-to-one association to VehicleTelemetry
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TLMTRY_KY")
    @JsonIgnore
    private VehicleTelemetry vehicleTelemetry;
}
