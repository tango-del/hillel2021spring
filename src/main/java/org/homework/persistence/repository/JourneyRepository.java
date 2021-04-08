package org.homework.persistence.repository;

import org.homework.persistence.entity.JourneyEntity;
import org.homework.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class JourneyRepository extends CommonRepository<JourneyEntity, Long> {

    protected JourneyRepository() {
        super(JourneyEntity.class);
    }

    @Override
    public JourneyEntity createOrUpdate(JourneyEntity entity) {
        if (Objects.isNull(entity)) throw new IllegalArgumentException("entity must be set");

        VehicleEntity vehicleEntity = entity.getVehicle();

        if (Objects.nonNull(vehicleEntity)) {
            if (!entityManager.contains(vehicleEntity)) {
                entity.setVehicle(entityManager.merge(vehicleEntity));
            }
        }
        return super.createOrUpdate(entity);
    }

/*    @Override
    public void removeById(Long id) {
        if (id == 0) return;

        final JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setId(id);
        super.remove(journeyEntity);
    }*/
}
