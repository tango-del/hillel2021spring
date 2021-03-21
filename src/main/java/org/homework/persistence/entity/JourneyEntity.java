package org.homework.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "journey")
@Getter
@Setter
public class JourneyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "station_from", nullable = false)
    private String stationFrom;

    @Column(name = "station_to", nullable = false)
    private String stationTo;

    @Column(name = "departure", nullable = false)
    private LocalDate departure;

    @Column(name = "arrival", nullable = false)
    private LocalDate arrival;

    @Column(name = "route", nullable = false)
    private String route;

    @Override
    public String toString() {
        return "JourneyEntity{" +
                "id=" + id +
                ", stationFrom='" + stationFrom + '\'' +
                ", stationTo='" + stationTo + '\'' +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", route='" + route + '\'' +
                '}';
    }
}
