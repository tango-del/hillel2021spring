package org.homework.service;

import org.homework.persistence.entity.VehicleEntity;
import org.homework.persistence.jpa.repository.VehicleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class TransactionalVehicleService {

    @Autowired
    private VehicleJpaRepository vehicleRepository;
    //private VehicleRepository vehicleRepository;
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
        return vehicleRepository.save(vehicleEntity);
    }

    @Transactional
    public void remove(final VehicleEntity vehicle) {
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle must be set");

        vehicleRepository.delete(vehicle);
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findByIds(Long... ids) {
        if (ids.length == 0) return Collections.emptyList();
        return (Collection<VehicleEntity>) vehicleRepository.findAllById(Arrays.asList(ids));
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
        return (Collection<VehicleEntity>) vehicleRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllByName(final String name) {
        if (StringUtils.isEmpty(name)) throw new IllegalArgumentException("name must be set");
        final Collection<VehicleEntity> byName = vehicleRepository.findByConditions(name, 1L, 30L);
        return byName;
    }
}
