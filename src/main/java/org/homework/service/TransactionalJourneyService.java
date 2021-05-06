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
        if (Objects.isNull(entity)) throw new IllegalArgumentException("entity must be set");

        System.out.println("create journey");
        final JourneyEntity orUpdate = journeyRepository.createOrUpdate(entity);

        //return orUpdate;

        System.out.println("get journey by id");
        JourneyEntity journey = journeyRepository.findById(orUpdate.getId()).get();

        System.out.println("remove journey by id");
        journeyRepository.removeById(journey.getId());

        journeyRepository.getEntityManager().flush();

        /*if (entity.getId() == 1) {
            throw new IllegalArgumentException();
        }*/

        JourneyEntity entity2 = new JourneyEntity();
        entity2.setDateTo(orUpdate.getDateTo());
        entity2.setDateFrom(orUpdate.getDateFrom());
        entity2.setStationFrom(orUpdate.getStationFrom());
        entity2.setStationTo(orUpdate.getStationTo());
        entity2.setActive(false);

        return journeyRepository.createOrUpdate(entity2);
    }

    @Transactional(readOnly = true)
    public Optional<JourneyEntity> findById(final Long id, boolean withDependencies) {
        if (Objects.isNull(id)) throw new IllegalArgumentException("id must be set");

        final Optional<JourneyEntity> byId = journeyRepository.findById(id);
        if (withDependencies && byId.isPresent()) {
            final JourneyEntity journeyEntity = byId.get();
            journeyEntity.getVehicle().getVehicleName();
            journeyEntity.getStops().size();
        }
        return byId;
    }

    @Transactional
    public void remove(JourneyEntity journey) {
        if (Objects.isNull(journey)) throw new IllegalArgumentException("journey must be set");

        journeyRepository.remove(journey);
    }

    @Transactional
    public void removeById(Long journeyId) {
        if (journeyId == 0) return;

        journeyRepository.removeById(journeyId);
    }
}
