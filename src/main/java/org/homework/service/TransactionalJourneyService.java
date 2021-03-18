package org.homework.service;

import org.homework.Journey;
import org.homework.persistence.entity.JourneyEntity;
import org.homework.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class TransactionalJourneyService {
//public class TransactionalJourneyService implements JourneyService {

    @Autowired
    private JourneyRepository journeyRepository;

//    @Override
//    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
//        return null;
//    }

    // TODO почему транзакции лучше ставить на наших сервисных сущностях чем в сущностях которые являются репозиторием
    @Transactional
    public Long createJourney(final JourneyEntity entity) {
        if (entity == null) throw new IllegalArgumentException("entity must be set");

        return journeyRepository.create(entity);
    }
}
