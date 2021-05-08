package org.homework.service;

import org.homework.persistence.entity.VehicleEntity;
import org.homework.persistence.entity.VehicleEntity_;
import org.homework.persistence.jpa.repository.VehicleJpaRepository;
import org.homework.persistence.jpa.repository.specification.VehicleSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        /*final Collection<VehicleEntity> byName = vehicleRepository.findByConditions(
                name,
                1L,
                30L,
                PageRequest.of(2, 3, Sort.by(VehicleEntity_.ID)));*/
        /*final Page<VehicleEntity> byName = vehicleRepository.findByConditionsParamNative(
                name,
                1L,
                30L,
                PageRequest.of(1, 3, Sort.by(VehicleEntity_.ID)));*/
//        return byName.getContent();

        //return vehicleRepository.findByName(name);

        return vehicleRepository.findOnlyActive();
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllByNameAndNotActive(final String name) {
        if (StringUtils.isEmpty(name)) throw new IllegalArgumentException("name must be set");

        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setVehicleName(name);
        vehicleEntity.setActive(false);
        vehicleEntity.setId(17L);

        final Example<VehicleEntity> example = Example.of(vehicleEntity);

        //return vehicleRepository.findAll(VehicleSpecification.byName(name).and(VehicleSpecification.onlyNoActive()));

        return vehicleRepository.findAll(VehicleSpecification.byNameAndExample(name, vehicleEntity));
    }

    @Transactional
    public void disableById(Long id) {
        if (id == 0) return;

        vehicleRepository.disableById(id);
    }
}
