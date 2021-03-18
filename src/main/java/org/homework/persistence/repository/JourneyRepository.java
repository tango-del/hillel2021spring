package org.homework.persistence.repository;

import org.homework.persistence.entity.JourneyEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JourneyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final JourneyEntity journeyEntity) {
        if (journeyEntity == null) throw new IllegalArgumentException("journeyEntity must be set");

        entityManager.persist(journeyEntity);
        return journeyEntity.getId();
    }
}
