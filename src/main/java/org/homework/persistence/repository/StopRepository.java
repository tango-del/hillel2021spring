package org.homework.persistence.repository;

import org.homework.persistence.entity.StopEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class StopRepository extends CommonRepository<StopEntity, Long> {

    protected StopRepository() {
        super(StopEntity.class);
    }
}
