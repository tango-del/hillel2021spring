package org.homework.service;

import org.homework.Journey;
import org.homework.persistence.entity.JourneyEntity;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;

public interface JourneyService {

    Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo);


    @Transactional
    Long createJourney(JourneyEntity entity);
}
