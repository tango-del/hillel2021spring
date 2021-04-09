package org.homework.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
public class VehicleEntity extends AbstractModifyEntity<Long> {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "vehicle")
    private Set<JourneyEntity> journeys = new HashSet<>();

    public void addJourney(final JourneyEntity journeyEntity) {
        if (Objects.isNull(journeyEntity)) throw new IllegalArgumentException("journeyEntity must be set");
        if (journeys == null) {
            journeys = new HashSet<>();
        }
        journeys.add(journeyEntity);
        journeyEntity.addVehicle(this);
    }

    public void removeAllJourney() {
        if (CollectionUtils.isEmpty(journeys)) return;
        journeys.forEach(item -> item.setVehicle(null));
    }

    //@Embedded
    //private CommonInfo commonInfo;


    @Override
    public String toString() {
        return "VehicleEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
