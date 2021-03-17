package org.homework.service;

import org.homework.Journey;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

// класс заглушка
@Component("stub")
@Lazy
@Order(1)
public class StubJourneyServiceImpl implements JourneyService {

    @Override
    public Collection<Journey> find(final String stationFrom, final String stationTo, final LocalDate dateFrom, final LocalDate dateTo) {

        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "StubJourneyServiceImpl class toString";
    }
}
