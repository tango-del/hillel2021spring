package org.homework.service;

import org.homework.Journey;

import java.time.LocalDate;
import java.util.Collection;

public interface JourneyService {

    <T> Collection<T> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo);

}
