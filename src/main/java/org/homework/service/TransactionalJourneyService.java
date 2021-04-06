package org.homework.service;

import org.homework.persistence.entity.JourneyEntity;
import org.homework.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service(value = "transactionalJourneyService")
public class TransactionalJourneyService {
//public class TransactionalJourneyService implements JourneyService {

    @Autowired
    private JourneyRepository journeyRepository;

    // TODO почему транзакции лучше ставить на наших сервисных сущностях чем в сущностях которые являются репозиторием
    @Transactional
    public JourneyEntity createOrUpdateJourney(final JourneyEntity entity) {
        if (entity == null) throw new IllegalArgumentException("entity must be set");

        return journeyRepository.createOrUpdate(entity);
    }

    @Transactional(readOnly = true)
    public Optional<JourneyEntity> findById(final Long id, boolean withDependencies) {
        if (id == null) throw new IllegalArgumentException("id must be set");

        final Optional<JourneyEntity> byId = journeyRepository.findById(id);
        if (withDependencies && byId.isPresent()) {
            final JourneyEntity journeyEntity = byId.get();
            journeyEntity.getVehicle().getName();
            journeyEntity.getStops().size();
        }
        return byId;
    }
}
