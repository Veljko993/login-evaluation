package eco.login.evaluation.dao.entity;

import eco.login.evaluation.common.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author vantonijevic
 * <p>
 * Entity used for storing vehicle data
 */
@Entity
@Table(name = "VHCL")
@Data
@Builder
@AllArgsConstructor
public class Vehicle implements Serializable {

    @Id
    @Column(name = "VHCL_KY")
    private String vehicleKey;

    @Basic
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "VHCL_TYPE_KY")
    private VehicleType vehicleType;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<VehicleTelemetry> vehicleTelemetries;

}
