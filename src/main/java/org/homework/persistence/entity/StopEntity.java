package org.homework.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stop")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
public class StopEntity extends AbstractModifyEntity<Long> {

    @Embedded
    private CommonInfo commonInfo;

    @OneToOne(mappedBy = "stop", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private StopAdditionalInfoEntity additionalInfo;

    @ManyToMany(mappedBy = "stops")
    private List<JourneyEntity> journeys = new ArrayList<>();

    @Transient
    private boolean applyToJourneyBuild;

    public void addStopAdditionalInfo(final StopAdditionalInfoEntity stopAdditionalInfo) {
        if (Objects.isNull(stopAdditionalInfo)) {
            this.additionalInfo = null;
            return;
        }
        stopAdditionalInfo.setStop(this);
        this.additionalInfo = stopAdditionalInfo;
    }

    public void addJourney(JourneyEntity journeyEntity) {
        if (Objects.isNull(journeyEntity)) return;
        if (journeys == null) journeys = new ArrayList<>();
        journeys.add(journeyEntity);
    }
}
