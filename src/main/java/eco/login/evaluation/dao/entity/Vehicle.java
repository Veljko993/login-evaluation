package eco.login.evaluation.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eco.login.evaluation.common.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class Vehicle implements Serializable {

    @Id
    @Column(name = "VHCL_KY")
    private String vehicleKey;

    @Basic
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "VHCL_TYPE_KY")
    private VehicleType vehicleType;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<VehicleTelemetry> vehicleTelemetries;

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleKey='" + vehicleKey + '\'' +
                ", vehicleType=" + vehicleType +
                ", vehicleTelemetries=omitted due to circularity" +
                '}';
    }
}
