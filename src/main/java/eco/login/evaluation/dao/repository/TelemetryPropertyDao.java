package eco.login.evaluation.dao.repository;

import eco.login.evaluation.dao.entity.TelemetryProperty;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vantonijevic
 * <p>
 * Repository for {@link TelemetryProperty} entity
 */
@Repository
public interface TelemetryPropertyDao extends CrudRepository<TelemetryProperty, Long> {
}
