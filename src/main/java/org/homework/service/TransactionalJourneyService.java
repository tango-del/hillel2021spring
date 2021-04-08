package org.homework.service;

import org.homework.persistence.entity.JourneyEntity;
import org.homework.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
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

        System.out.println("create journey");
        final JourneyEntity orUpdate = journeyRepository.createOrUpdate(entity);
        System.out.println("get journey by id");
        JourneyEntity journey = journeyRepository.findById(orUpdate.getId()).get();
        System.out.println("remove journey by id");
        //journeyRepository.removeById(journey.getId());

        //boolean isNew = Objects.isNull(entity.getId());
        //if (!isNew)
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

    @Transactional
    public void remove(JourneyEntity journey) {
        if (journey == null) throw new IllegalArgumentException("journey must be set");

        journeyRepository.remove(journey);
    }

    @Transactional
    public void removeById(Long journeyId) {
        if (journeyId == 0) return;

        journeyRepository.removeById(journeyId);
    }
}
