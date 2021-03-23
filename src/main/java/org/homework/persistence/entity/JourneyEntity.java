package org.homework.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "journey")
@Getter
@Setter
@NoArgsConstructor
public class JourneyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "station_from", nullable = false, columnDefinition = "varchar(1050) default 'NOT_SPECIFIED'")
    private String stationFrom;

    @Column(name = "station_to", nullable = false, columnDefinition = "varchar(1050) default 'NOT_SPECIFIED'")
    private String stationTo;

    @Column(name = "date_from", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    @Column(name = "date_to", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateTo;

    @Override
    public String toString() {
        return "JourneyEntity{" +
                "id=" + id +
                ", stationFrom='" + stationFrom + '\'' +
                ", stationTo='" + stationTo + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
