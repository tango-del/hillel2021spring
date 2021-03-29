package org.homework.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
public class VehicleEntity extends AbstractModifyEntity<Long> {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "vehicle")
    private List<JourneyEntity> journeys = new ArrayList<>();

    //@Embedded
    //private CommonInfo commonInfo;
}
