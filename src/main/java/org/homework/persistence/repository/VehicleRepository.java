package org.homework.persistence.repository;

import org.homework.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class VehicleRepository extends CommonRepository<VehicleEntity, Long> {

    protected VehicleRepository() {
        super(VehicleEntity.class);
    }
}
