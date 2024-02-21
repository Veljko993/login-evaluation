package eco.login.evaluation.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;

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
    //DateTime
    //Vehicle - serial num
    //GPS long
    //GPS lat
    //total work hours
}
