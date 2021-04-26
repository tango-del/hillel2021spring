package org.homework.persistence.repository;

import org.hibernate.query.criteria.internal.OrderImpl;
import org.homework.persistence.entity.JourneyEntity_;
import org.homework.persistence.entity.VehicleEntity;
import org.homework.persistence.entity.VehicleEntity_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Objects;

@Repository
public class VehicleRepository extends CommonRepository<VehicleEntity, Long> {

    protected VehicleRepository() {
        super(VehicleEntity.class);
    }

    @Override
    public void remove(VehicleEntity entity) {
        if (Objects.isNull(entity)) throw new IllegalArgumentException("entity must be set");

        entity = findById(entity.getId()).get();
        //entity.removeAllJourney();
        super.remove(entity);
    }

    @Override
    public Collection<VehicleEntity> findByName(String name) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<VehicleEntity> query = criteriaBuilder.createQuery(VehicleEntity.class);
        final Root<VehicleEntity> from = query.from(VehicleEntity.class);
        final Join<Object, Object> journeys = from.join(VehicleEntity_.JOURNEYS, JoinType.LEFT);
        final Predicate byJourneyName = criteriaBuilder.equal(journeys.get(JourneyEntity_.STATION_FROM), criteriaBuilder.parameter(String.class, "stationFromParam"));
        journeys.on(byJourneyName);
        final Predicate byName = criteriaBuilder.equal(from.get(VehicleEntity_.NAME), criteriaBuilder.parameter(String.class, "nameParam"));
        final Predicate active = criteriaBuilder.equal(from.get(VehicleEntity_.ACTIVE), criteriaBuilder.parameter(Boolean.class, "activeParam"));
        return entityManager.createQuery(query.
                select(from).
                where(byName, active).orderBy(new OrderImpl(from.get(VehicleEntity_.ID), false))).
                setParameter("nameParam", name).
                setParameter("activeParam", true).
                setParameter("stationFromParam", "Odessa").
                setFirstResult(2).
                setMaxResults(3).
                getResultList();

        /*return entityManager.createQuery("select v from VehicleEntity v left join v.journeys js on js.vehicle.id = v.id order by v.id desc", VehicleEntity.class)
                .setFirstResult(2)
                .setMaxResults(3)
                .getResultList();*/
    }

/*        @Override
    public Collection<VehicleEntity> findAll() {
        System.out.println("System.out.println("call findAll() in VehicleRepository");");
        //return entityManager.createNamedQuery("findAll", VehicleEntity.class).getResultList();
        return entityManager.createNamedStoredProcedureQuery("findAllVehicles").getResultList();
    }*/
}
