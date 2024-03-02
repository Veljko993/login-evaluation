package eco.login.evaluation.dao.repository;

import eco.login.evaluation.dao.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vantonijevic
 * <p>
 * Repository for {@link Vehicle} entity
 */
@Repository
public interface VehicleDao extends CrudRepository<Vehicle, String> {
}
