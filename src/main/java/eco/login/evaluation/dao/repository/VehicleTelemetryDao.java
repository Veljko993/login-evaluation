package eco.login.evaluation.dao.repository;

import eco.login.evaluation.dao.entity.VehicleTelemetry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vantonijevic
 * <p>
 * Repository for {@link VehicleTelemetry} entity
 */
@Repository
public interface VehicleTelemetryDao extends CrudRepository<VehicleTelemetry, Long> {
}
