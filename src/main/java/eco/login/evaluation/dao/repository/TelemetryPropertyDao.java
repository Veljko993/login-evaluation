package eco.login.evaluation.dao.repository;

import eco.login.evaluation.dao.entity.TelemetryProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vantonijevic
 * <p>
 * Repository for {@link TelemetryProperty} entity
 */
@Repository
public interface TelemetryPropertyDao extends CrudRepository<TelemetryProperty, Long> {
}
