package org.homework.service;

import org.homework.persistence.entity.JourneyEntity;
import org.homework.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service(value = "transactionJourneyService")
public class TransactionalJourneyService implements JourneyService {

    @Autowired
    private JourneyRepository journeyRepository;

    @Override
    @Transactional
    public List<JourneyEntity> find(final String stationFrom, final String stationTo, final LocalDate dateFrom, final LocalDate dateTo) {
        if (stationFrom == null) throw new IllegalArgumentException("station from must be set");
        if (stationTo == null) throw new IllegalArgumentException("station to must be set");
        if (dateFrom == null) throw new IllegalArgumentException("date from must be set");
        if (dateTo == null) throw new IllegalArgumentException("date to must be set");

        List<JourneyEntity> journeys = journeyRepository.getJourneys(stationFrom, stationTo);
        if (journeys.isEmpty()) return Collections.emptyList();

        return checkJourneys(journeys, dateFrom, dateTo);
    }

    @Override
    @Transactional
    public Long createJourney(final JourneyEntity entity) {
        if (entity == null) throw new IllegalArgumentException("entity must be set");

        if (!checkFillEntity(entity)) {
            return journeyRepository.create(entity);
        } else {
            return 0L;
        }
    }

    private boolean checkFillEntity(final JourneyEntity entity) {
        return (StringUtils.isEmpty(entity.getStationFrom())
                || StringUtils.isEmpty(entity.getStationTo())
                || StringUtils.isEmpty(entity.getRoute())
                || entity.getDeparture() == null
                || entity.getArrival() == null);
    }

    private List<JourneyEntity> checkJourneys(final Collection<JourneyEntity> journeys, final LocalDate dateFrom, final LocalDate dateTo) {
        if (journeys == null) throw new IllegalArgumentException("Collection journeys must be set");
        if (dateFrom == null) throw new IllegalArgumentException("dateFrom must be set");
        if (dateTo == null) throw new IllegalArgumentException("dateTo must be set");

        final List<JourneyEntity> list = new ArrayList<>();

        for (JourneyEntity entity : journeys) {
            if (entity.getArrival().equals(dateTo) && entity.getDeparture().equals(dateFrom)) {
                list.add(entity);
            }
        }
        if (list.isEmpty()) return Collections.emptyList();
        return Collections.unmodifiableList(list);
    }
}
