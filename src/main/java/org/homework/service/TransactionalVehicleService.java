package org.homework.service;

import org.homework.persistence.entity.VehicleEntity;
import org.homework.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class TransactionalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public VehicleEntity createOrUpdate(final VehicleEntity vehicleEntity) {
        if (Objects.isNull(vehicleEntity)) throw new IllegalArgumentException("vehicleEntity must be set");

        return vehicleRepository.createOrUpdate(vehicleEntity);
    }

    @Transactional
    public void remove(final VehicleEntity vehicle) {
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle must be set");

        vehicleRepository.remove(vehicle);
    }
}
