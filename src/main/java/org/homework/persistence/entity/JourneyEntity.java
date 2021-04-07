package org.homework.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.homework.persistence.entity.enums.DirectionType;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "journey")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class JourneyEntity extends AbstractModifyEntity<Long> {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JourneyEntity)) return false;
        JourneyEntity entity = (JourneyEntity) o;
        return getId() != null && Objects.equals(getId(), entity.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
        //return Objects.hash(stationFrom, stationTo, dateFrom, dateTo, direction, vehicle);
    }

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    public void addVehicle(final VehicleEntity vehicle) {
        if (vehicle == null) throw new IllegalArgumentException("vehicle must be set");

        this.vehicle = vehicle;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "journey_stop", indexes = @Index(name = "journey_stop_idx", columnList = "journey_id, stop_id"),
            joinColumns = @JoinColumn(name = "journey_id"),
            inverseJoinColumns = @JoinColumn(name = "stop_id")
    )
    private List<StopEntity> stops = new ArrayList<>();

    public void addStop(final StopEntity stop) {
        if (stop == null) return;
        if (stops == null) stops = new ArrayList<>();
        stops.add(stop);
        stop.addJourney(this);
    }

    @Override
    public String toString() {
        return "JourneyEntity{" +
                "stationFrom='" + stationFrom + '\'' +
                ", stationTo='" + stationTo + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", direction=" + direction +
                ", vehicle=" + vehicle +
                '}';
    }
}
