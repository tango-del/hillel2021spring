package org.homework.persistence.repository;

import org.homework.persistence.entity.StopEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class StopRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final StopEntity stopEntity) {
        if (stopEntity == null) throw new IllegalArgumentException("stopEntity must be set");

        entityManager.persist(stopEntity);
        return stopEntity.getId();
    }
}
