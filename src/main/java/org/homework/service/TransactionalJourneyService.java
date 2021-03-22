package org.homework.service;

import org.homework.Journey;
import org.homework.persistence.entity.JourneyEntity;
import org.homework.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service("transactionJourneyService")
//public class TransactionalJourneyService {
public class TransactionalJourneyService implements JourneyService {

    @Autowired
    private JourneyRepository journeyRepository;

    @Override
    @Transactional
    public Collection<JourneyEntity> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        Collection<JourneyEntity> journeys = journeyRepository.getJourneys(stationFrom, stationTo);
        if (journeys.isEmpty()) return Collections.emptyList();

        Collection<JourneyEntity> journeyEntities = checkJourneys(journeys, dateFrom, dateTo);
        return journeyEntities;
    }

    private Collection<JourneyEntity> checkJourneys(Collection<JourneyEntity> journeys, LocalDate dateFrom, LocalDate dateTo) {
        List<JourneyEntity> list = new ArrayList<>();

        for (JourneyEntity entity : journeys) {
            if (entity.getArrival().equals(dateTo) && entity.getDeparture().equals(dateFrom)) {
                list.add(entity);
            }
        }
        if (list.isEmpty()) return Collections.emptyList();
        return list;
    }

    // TODO почему транзакции лучше ставить на наших сервисных сущностях чем в сущностях которые являются репозиторием
    @Transactional
    public Long createJourney(final JourneyEntity entity) {
        if (entity == null) throw new IllegalArgumentException("entity must be set");

        return journeyRepository.create(entity);
    }

//    @Transactional
//    public Collection<JourneyEntity> findJourney(String stationFrom, String stationTo) {
//        return journeyRepository.getJourneys(route);
//    }
}
