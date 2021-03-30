package org.homework.service;

import org.homework.Journey;
import org.homework.persistence.entity.JourneyEntity;
import org.homework.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
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

        final Optional<JourneyEntity> byId = journeyRepository.frindById(id);
        if (withDependencies && byId.isPresent()) {
            final JourneyEntity journeyEntity = byId.get();
            System.out.println(journeyEntity.getVehicle().getName());

        }
        return byId;
    }
}

