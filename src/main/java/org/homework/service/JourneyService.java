package org.homework.service;

import org.homework.persistence.entity.JourneyEntity;

import java.time.LocalDate;
import java.util.Collection;

public interface JourneyService {

    Collection<JourneyEntity> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo);

    Long createJourney(JourneyEntity entity);
}
