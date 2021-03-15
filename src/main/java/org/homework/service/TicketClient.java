package org.homework.service;

import org.homework.Journey;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;

// клиентский сервис который будет дёргать JourneyService
public class TicketClient {

    private JourneyService journeyService; // не будем оперировать какой-то конкретно реализацией, а интерфейсом

    public TicketClient(@Qualifier("inMemoryJourneyService") JourneyService journeyService) {
//    public TicketClient(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    public Collection<Journey> find(final String stationFrom, final String stationTo, final LocalDate dateFrom, final LocalDate dateTo) {
        if (stationFrom == null) throw new IllegalArgumentException("station from must be set");
        if (stationTo == null) throw new IllegalArgumentException("station to must be set");
        if (dateFrom == null) throw new IllegalArgumentException("date from must be set");
        if (dateTo == null) throw new IllegalArgumentException("date to must be set");

        return journeyService.find(stationFrom, stationTo, dateFrom, dateTo);
    }

    public static void main(String[] args) {

        final JourneyService journeyService = new InMemoryJourneyServiceImpl();
        final TicketClient client = new TicketClient(journeyService);

        System.out.println(client.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));

        // можем оберуть в какой-то Exception красивый для клиента
        System.out.println(client.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(6)));
    }
}
