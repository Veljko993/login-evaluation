package eco.login.evaluation.dao.repository;

import eco.login.evaluation.dao.entity.TelemetryPropertyDefinition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vantonijevicO
 * <p>
 * Repository for {@link TelemetryPropertyDefinition} entity
 */
@Repository
public interface TelemetryPropertyDefinitionDao extends CrudRepository<TelemetryPropertyDefinition, Long> {
}
