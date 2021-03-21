package org.homework.persistence.repository;

import org.homework.persistence.entity.JourneyEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
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

    public Collection<JourneyEntity> getJourneys(String route) {

        List<JourneyEntity> resultList = entityManager.createQuery("select journey from JourneyEntity journey").getResultList();

        if (resultList.isEmpty()) return Collections.emptyList();

        return Collections.unmodifiableList(resultList);
//        System.out.println(entityManager.find(JourneyEntity.class, 1L).toString());
        //entityManager.createQuery("select journey from JourneyEntity journey where route = 'Odessa->Kiev'").getResultList().forEach(System.out::println);
        //List list = entityManager.createQuery("select journey from JourneyEntity").getResultList();
        //return list;
    }
}
