package eco.login.evaluation.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * @author vantonijevic
 *
 * Entity used for storing telemetry data. May be modified in the future.
 */
@Entity
@Table(name = "VHCL_TLMTRY")
@AllArgsConstructor
@Data
@Builder
public class VehicleTelemetry implements Serializable {
    @Id
    @SequenceGenerator(name = "VHCL_TLMTRY_KEY_GENERATOR", sequenceName = "SEQ_VHCL_TLMTRY", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VHCL_TLMTRY_KEY_GENERATOR")
    @Column(name = "VHCL_TLMTRY_KY")
    private Long vehicleTelemetryKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VHCL_KY")
    @JsonIgnore
    private Vehicle vehicle;

    //TODO Think if you will store these properties directly or in prop table: DateTime, GPS long, GPS lat, total work hours
}
