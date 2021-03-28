package org.homework.service;

import org.homework.persistence.entity.JourneyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Component
@Lazy
public class TicketClient {

    @Autowired
    private Map<String, JourneyService> journeyServices;

    public Long createJourney(final JourneyEntity journeyEntity) {
        if (journeyEntity == null) throw new IllegalArgumentException("journeyEntity must be set");
        if (journeyServices.containsKey("transactionJourneyService")) {
            return journeyServices.get("transactionJourneyService").createJourney(journeyEntity);
        } else {
            return 0L;
        }
    }

    public Collection<JourneyEntity> find(final String stationFrom, final String stationTo, final LocalDate dateFrom, final LocalDate dateTo) {
        if (stationFrom == null) throw new IllegalArgumentException("station from must be set");
        if (stationTo == null) throw new IllegalArgumentException("station to must be set");
        if (dateFrom == null) throw new IllegalArgumentException("date from must be set");
        if (dateTo == null) throw new IllegalArgumentException("date to must be set");

        for (JourneyService service : journeyServices.values()) {

            final Collection<JourneyEntity> journeys = service.find(stationFrom, stationTo, dateFrom, dateTo);

            if (!journeys.isEmpty()) return Collections.unmodifiableCollection(journeys);
        }

        return  Collections.emptyList();
    }
}
