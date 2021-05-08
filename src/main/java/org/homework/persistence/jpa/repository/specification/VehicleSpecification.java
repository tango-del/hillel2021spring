package org.homework.persistence.jpa.repository.specification;

import org.homework.persistence.entity.VehicleEntity;
import org.homework.persistence.entity.VehicleEntity_;
import org.springframework.data.jpa.domain.Specification;

public interface VehicleSpecification {

    static Specification<VehicleEntity> byName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(VehicleEntity_.VEHICLE_NAME), criteriaBuilder.literal(name));
    }
}
