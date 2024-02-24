package eco.login.evaluation;

import eco.login.evaluation.dao.entity.TelemetryPropertyDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelemetryPropertyDefinitionDao extends JpaRepository<TelemetryPropertyDefinition, Integer> {
}
