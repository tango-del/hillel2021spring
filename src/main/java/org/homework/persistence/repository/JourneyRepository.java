package org.homework.persistence.repository;

import org.hibernate.Session;
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

    public Optional<JourneyEntity> findById(final Long id) {
        if (id == null) throw new IllegalArgumentException("id must be set");

        Session session = entityManager.unwrap(Session.class);
        //final JourneyEntity entity = session.byMultipleIds(JourneyEntity.class).multiLoad(id).get(0);

        final JourneyEntity value = session.find(JourneyEntity.class, id);

        //final JourneyEntity value = entityManager.find(JourneyEntity.class, id);
        return Optional.ofNullable(value);
    }

    public JourneyEntity save(JourneyEntity journey) {
        if (journey == null) throw new IllegalArgumentException("journey must be set");

        final JourneyEntity merge = entityManager.merge(journey);
        //entityManager.flush();
        return merge;
    }
}
