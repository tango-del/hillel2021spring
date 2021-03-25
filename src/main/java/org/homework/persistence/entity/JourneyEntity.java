package org.homework.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.homework.persistence.entity.enums.DirectionType;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "journey")
@Getter
@Setter
@NoArgsConstructor
public class JourneyEntity extends AbstractModifyEntity<Long> {

    @Column(name = "station_from", nullable = false, columnDefinition = "varchar(1050) default 'NOT_SPECIFIED'")
    private String stationFrom;

    @Column(name = "station_to", nullable = false, columnDefinition = "varchar(1050) default 'NOT_SPECIFIED'")
    private String stationTo;

    /*
    JAVA.UTIL - BEFORE JAVA 8
    @Column(name = "date_from", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    @Column(name = "date_to", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateTo;
     */

    @Column(name = "date_from", nullable = false)
    private Instant dateFrom;

    @Column(name = "date_to", nullable = false)
    private Instant dateTo;

    @Column(name = "direction", length = 20)
    @Enumerated(EnumType.STRING)
    private DirectionType direction = DirectionType.TO;
}
