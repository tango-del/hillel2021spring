package org.homework.service;

import org.homework.Journey;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

// класс заглушка
//@Component("stub")
public class StubJourneyServiceImpl implements JourneyService {

    @Override
    public Collection<Journey> find(final String stationFrom, final String stationTo, final LocalDate dateFrom, final LocalDate dateTo) {

        return Collections.emptyList();
    }
}
