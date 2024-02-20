package eco.login.evaluation.dao.entity;

import eco.login.evaluation.common.VehicleType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table
@Data
@Builder
public class Vehicle implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "VEHICLE_KEY_GENERATOR", sequenceName = "SEQ_VEHICLE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEHICLE_KEY_GENERATOR")
    @Column(name = "VEHICLE_KY")
    private Long vehicleKey;

    @Column(name = "VEHICLE_TYPE")
    private VehicleType vehicleType;



}
