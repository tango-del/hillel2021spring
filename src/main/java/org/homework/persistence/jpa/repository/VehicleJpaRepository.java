package org.homework.persistence.jpa.repository;

import org.homework.persistence.entity.VehicleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface VehicleJpaRepository extends CrudRepository<VehicleEntity, Long> {

    Collection<VehicleEntity> findByVehicleName(String name);
}
