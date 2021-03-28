package org.homework.persistence.repository;

import org.homework.persistence.entity.JourneyEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
public class JourneyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final JourneyEntity journeyEntity) {
        if (journeyEntity == null) throw new IllegalArgumentException("journeyEntity must be set");

        entityManager.persist(journeyEntity);
        return journeyEntity.getId();
    }

    public List<JourneyEntity> getJourneys(final String stationFrom, final String stationTo) {
        if (StringUtils.isEmpty(stationFrom)) throw new IllegalArgumentException("stationFrom must be set");
        if (StringUtils.isEmpty(stationTo)) throw new IllegalArgumentException("stationTo must be st");

        final List<JourneyEntity> list = entityManager
                .createQuery("select journey from JourneyEntity journey where stationFrom = ?1 and stationTo = ?2")
                .setParameter(1, stationFrom)
                .setParameter(2, stationTo)
                .getResultList();

        if (list.isEmpty()) return Collections.emptyList();

        return Collections.unmodifiableList(list);
    }
}
