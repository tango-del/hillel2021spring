package org.homework.persistence.repository;

import org.homework.persistence.entity.JourneyEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class JourneyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final JourneyEntity journeyEntity) {
        if (journeyEntity == null) throw new IllegalArgumentException("journeyEntity must be set");

        entityManager.persist(journeyEntity);
        return journeyEntity.getId();
    }

    public Optional<JourneyEntity> frindById(final Long id) {
        if (id == null) throw new IllegalArgumentException("id must be set");

        return Optional.ofNullable(entityManager.find(JourneyEntity.class, id));
    }
}
