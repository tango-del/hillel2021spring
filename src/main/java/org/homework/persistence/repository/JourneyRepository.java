package org.homework.persistence.repository;

import org.hibernate.Session;
import org.homework.persistence.entity.JourneyEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class JourneyRepository extends CommonRepository<JourneyEntity, Long> {

    protected JourneyRepository() {
        super(JourneyEntity.class);
    }
}
