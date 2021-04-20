package org.homework.persistence.repository;

import org.homework.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

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

//    @Override
//    public Collection<VehicleEntity> findAll() {
//        System.out.println("System.out.println("call findAll() in VehicleRepository");");
//        //return entityManager.createNamedQuery("findAll", VehicleEntity.class).getResultList();
//        return entityManager.createNamedStoredProcedureQuery("findAllVehicles").getResultList();
//    }
}
