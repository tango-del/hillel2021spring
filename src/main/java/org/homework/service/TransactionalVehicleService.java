package org.homework.service;

import org.homework.persistence.entity.VehicleEntity;
import org.homework.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
/*
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @PersistenceContext
    private EntityManagerFactory entityManagerFactory;*/

    @Transactional
    public VehicleEntity createOrUpdate(final VehicleEntity vehicleEntity) {
        if (Objects.isNull(vehicleEntity)) throw new IllegalArgumentException("vehicleEntity must be set");

/*        EntityManager entityManager = entityManagerFactory.createEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(vehicleEntity);
        transaction.commit();
        transaction.rollback();

        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        final TransactionStatus transaction1 = platformTransactionManager.getTransaction(transactionTemplate);
        final VehicleEntity orUpdate = vehicleRepository.createOrUpdate(vehicleEntity);
        platformTransactionManager.commit(transaction1);
        platformTransactionManager.rollback(transaction1);
        return orUpdate;
        return transactionTemplate.execute((status) -> vehicleRepository.createOrUpdate(vehicleEntity));*/
        return vehicleRepository.createOrUpdate(vehicleEntity);
    }

    @Transactional
    public void remove(final VehicleEntity vehicle) {
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle must be set");

        vehicleRepository.remove(vehicle);
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findByIds(Long... ids) {
        if (ids.length == 0) return Collections.emptyList();
        return vehicleRepository.findByIds(ids);
    }

    @Transactional(readOnly = true)
    public Optional<VehicleEntity> findById(Long id, boolean withDependencies) {
        if (id.equals(0L)) return Optional.empty();
        final Optional<VehicleEntity> byId = vehicleRepository.findById(id);
        if (!byId.isPresent()) return byId;
        if (!withDependencies) return byId;
        byId.get().getJourneys().size();
        return byId;
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAll() {
        return vehicleRepository.findAll();
    }

    @Autowired
    private NewTransactionalVehicleService newTransactionalVehicleService;

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllByName(final String name) {
        if (StringUtils.isEmpty(name)) throw new IllegalArgumentException("name must be set");
        final Collection<VehicleEntity> byName = vehicleRepository.findByName(name);
        final VehicleEntity next = byName.iterator().next();
        next.setName(String.valueOf(System.currentTimeMillis()));
        System.out.printf("save vehicle with id = %s and new value %s \n", next.getId(), next.getName());
        newTransactionalVehicleService.createOrUpdate(next);
        return byName;
    }
}
