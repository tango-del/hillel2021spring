package org.homework.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@NamedQueries(value = {
        @NamedQuery(name = "findAll", query = "from VehicleEntity ")
})
public class VehicleEntity extends AbstractModifyEntity<Long> {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "vehicle")//, cascade = {CascadeType.REMOVE})
    private Set<JourneyEntity> journeys = new HashSet<>();

    public void addJourney(final JourneyEntity journeyEntity) {
        if (Objects.isNull(journeyEntity)) throw new IllegalArgumentException("journeyEntity must be set");
        if (journeys == null) {
            journeys = new HashSet<>();
        }
        journeys.add(journeyEntity);
        journeyEntity.addVehicle(this);
    }

/*    public void removeAllJourney() {
        if (CollectionUtils.isEmpty(journeys)) return;
        journeys.forEach(item -> item.setVehicle(null));
    }*/

    //@Embedded
    //private CommonInfo commonInfo;


    @Override
    public String toString() {
        return "VehicleEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
