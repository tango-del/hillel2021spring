package org.homework.service;

import org.homework.persistence.entity.VehicleEntity;
import org.homework.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public VehicleEntity createOrUpdate(final VehicleEntity vehicleEntity) {
        if (vehicleEntity == null) throw new IllegalArgumentException("vehicleEntity must be set");

        return vehicleRepository.createOrUpdate(vehicleEntity);
    }
}
