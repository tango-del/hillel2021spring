package org.homework;

import java.time.LocalDate;
import java.util.Objects;

public class Journey {

    private final String stationFrom;
    private final String stationTo;
    private final LocalDate departure; // время отправления
    private final LocalDate arrival; // время прибытия
    private final String route; // название маршрута

    public Journey(final String stationFrom, final String stationTo, final LocalDate departure, final LocalDate arrival) {
        if (stationFrom == null) throw new IllegalArgumentException("station from must be set");
        if (stationTo == null) throw new IllegalArgumentException("station to must be set");
        if (departure == null) throw new IllegalArgumentException("departure date must be set");
        if (arrival == null) throw new IllegalArgumentException("arrival date must be set");

        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
        this.departure = departure;
        this.arrival = arrival;
        this.route = stationFrom + "->" + stationTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return Objects.equals(stationFrom, journey.stationFrom) &&
                Objects.equals(stationTo, journey.stationTo) &&
                Objects.equals(departure, journey.departure) &&
                Objects.equals(arrival, journey.arrival) &&
                Objects.equals(route, journey.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationFrom, stationTo, departure, arrival, route);
    }

    public String getStationFrom() {
        return stationFrom;
    }

    public String getStationTo() {
        return stationTo;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public String getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "stationFrom='" + stationFrom + '\'' +
                ", stationTo='" + stationTo + '\'' +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", route='" + route + '\'' +
                '}';
    }
}
