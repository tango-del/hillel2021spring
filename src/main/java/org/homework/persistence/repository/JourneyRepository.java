package org.homework.persistence.repository;

import com.zaxxer.hikari.pool.HikariPool;
import org.homework.persistence.entity.JourneyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
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

    public Collection<JourneyEntity> getJourneys(String stationFrom, String stationTo) {


        List<JourneyEntity> resultList = entityManager.createQuery("select journey from JourneyEntity journey where stationFrom = ?1 and stationTo = ?2")
                .setParameter(1, stationFrom)
                .setParameter(2, stationTo)
                .getResultList();

        if (resultList.isEmpty()) return Collections.emptyList();

        return Collections.unmodifiableList(resultList);
//        System.out.println(entityManager.find(JourneyEntity.class, 1L).toString());
        //entityManager.createQuery("select journey from JourneyEntity journey where route = 'Odessa->Kiev'").getResultList().forEach(System.out::println);
        //List list = entityManager.createQuery("select journey from JourneyEntity").getResultList();
        //return list;
    }
}
