package eco.login.evaluation.dao.entity;

import eco.login.evaluation.common.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="VHCL")
@Data
@Builder
@AllArgsConstructor
public class Vehicle implements Serializable {

    @Id
    @SequenceGenerator(name = "VHCL_KEY_GENERATOR", sequenceName = "SEQ_VHCL", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VHCL_KEY_GENERATOR")
    @Column(name = "VHCL_KY")
    private Long vehicleKey;

    @Basic @Enumerated(EnumType.ORDINAL) @Column(name = "VHCL_TYPE_KY")
    private VehicleType vehicleType;

    @OneToMany(mappedBy = "VHCL_TLMTRY", cascade = CascadeType.ALL) private List<VehicleTelemetry> vehicleTelemetries;

}
