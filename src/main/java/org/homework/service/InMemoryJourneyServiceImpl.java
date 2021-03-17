package org.homework.service;

import org.homework.Journey;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component("inMemoryJourneyService")
@Lazy
@Order(2)
public class InMemoryJourneyServiceImpl implements JourneyService {

//    private final String id;
    public InMemoryJourneyServiceImpl() {
        System.out.println("call constructor InMemoryJourneyServiceImpl");
//        this.id = id;
    }

    /*
            На один маршрут может быть несколько Journey
            например:
            Одесса - Киев может иметь несколько маршрутов
             */
    private Map<String, List<Journey> > storage = new HashMap<>();

    // выполнится при инициализации объекта класса
    {
        storage.put("odessa->kiev", createJourney("Odessa", "Kiev"));
        storage.put("kiev->odessa", createJourney("Kiev", "Odessa"));
        storage.put("lviv->kiev", createJourney("Lviv", "Kiev"));
    }

    private List<Journey> createJourney(final String from, final String to) {
        if (from == null) throw new IllegalArgumentException("String from must be set");
        if (to == null) throw new IllegalArgumentException("String to must be set");

        // гарантируем что коллекция будет неизменной
        final List<Journey> journeys = Arrays.asList(
                new Journey(from, to, LocalDate.now(), LocalDate.now().plusDays(1)),
                new Journey(from, to, LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)),
                new Journey(from, to, LocalDate.now().plusDays(2), LocalDate.now().plusDays(3))
        );
        return journeys;
    }

    /*
      смотрим по направлению, если такое направление есть в маршрутах то начинаем сравнивать
      какие маршруты подходят по времени, все эти маршруты формирвем в коллекцию и потом эту коллекцию отдаём
      Метод не должен кроме как отображения на клиенте что-то делать
     */
    @Override
    public Collection<Journey> find(final String stationFrom, final String stationTo, final LocalDate dateFrom, final LocalDate dateTo) {
        // если storage пустой то нечего искать
        if (storage == null || storage.isEmpty()) return Collections.emptyList();

        if (stationFrom == null) throw new IllegalArgumentException("station from must be set");
        if (stationTo == null) throw new IllegalArgumentException("station to must be set");
        if (dateFrom == null) throw new IllegalArgumentException("date from must be set");
        if (dateTo == null) throw new IllegalArgumentException("date to must be set");

        // допусукаем что stationFrom и stationTo могут быть приняты в любом регистре
        List<Journey> journeys = storage.get(stationFrom.toLowerCase() + "->" + stationTo.toLowerCase());

        // если ничего не нашли то возвращать нечего
        if (journeys == null || journeys.isEmpty()) return Collections.emptyList();

        List<Journey> out = new ArrayList<>();

        for (Journey item : journeys) {
            if (item.getDeparture().equals(dateFrom) && item.getArrival().equals(dateTo)) {
                out.add(item);
            }
        }
        /* должны быть уверенны что с этой коллекцией ничего не нужно делать
           туда ничего не нужно добавлять
        */
        return Collections.unmodifiableList(out);
    }

    @Override
    public String toString() {
        return "InMemoryJourneyServiceImpl class toString";
    }
}
