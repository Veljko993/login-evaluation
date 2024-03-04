package eco.login.evaluation.dao.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class VehicleDataDao {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    public List<?> executeNativeQuery(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        return Collections.unmodifiableList(query.getResultList());
    }
}
