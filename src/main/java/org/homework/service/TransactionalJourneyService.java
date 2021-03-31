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
    public Long createJourney(final JourneyEntity entity) {
        if (entity == null) throw new IllegalArgumentException("entity must be set");

        return journeyRepository.create(entity);
    }

    @Transactional(readOnly = true)
    public Optional<JourneyEntity> getById(final Long id, boolean withDependencies) {
        if (id == null) throw new IllegalArgumentException("id must be set");

        final Optional<JourneyEntity> byId = journeyRepository.findById(id);
        if (withDependencies && byId.isPresent()) {
            final JourneyEntity journeyEntity = byId.get();
            journeyEntity.getVehicle().getName();
            journeyEntity.getStops().size();
        }
        return byId;
    }

    @Transactional
    public void save(final JourneyEntity journey) {
        if (journey == null) throw new IllegalArgumentException("journey must be set");

//        final JourneyEntity save = journeyRepository.findById(journey.getId()).get();
//        save.setDirection(journey.getDirection());

        final JourneyEntity save = journeyRepository.save(journey);
        save.setStationFrom("test station from");
    }
}
