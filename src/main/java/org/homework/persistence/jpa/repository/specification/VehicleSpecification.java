package org.homework.persistence.jpa.repository.specification;

import org.homework.persistence.entity.VehicleEntity;
import org.homework.persistence.entity.VehicleEntity_;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;

public interface VehicleSpecification {

    static Specification<VehicleEntity> byName(final String name) {
        if (StringUtils.isEmpty(name)) throw new IllegalArgumentException("name must be set");

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(VehicleEntity_.VEHICLE_NAME), criteriaBuilder.literal(name));
    }

    static Specification<VehicleEntity> byNameAndExample(final String name, final VehicleEntity vehicle) {
        if (StringUtils.isEmpty(name)) throw new IllegalArgumentException("name must be set");
        if (vehicle == null) throw new IllegalArgumentException("vehicle must be set");

        return (root, query, criteriaBuilder) -> {
            final Predicate byName = criteriaBuilder.equal(root.get(VehicleEntity_.VEHICLE_NAME), criteriaBuilder.literal(name));
            final Predicate byExample = QueryByExamplePredicateBuilder.getPredicate(root, criteriaBuilder, Example.of(vehicle));

            return criteriaBuilder.and(byName, byExample);
        };
    }

    static Specification<VehicleEntity> onlyNoActive() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get(VehicleEntity_.ACTIVE));
    }

    static Specification<VehicleEntity> onlyActive() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(VehicleEntity_.ACTIVE));
    }
}
