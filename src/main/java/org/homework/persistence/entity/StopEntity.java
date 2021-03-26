package org.homework.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "stop")
@Getter
@Setter
@NoArgsConstructor
public class StopEntity extends AbstractModifyEntity<Long> {

    @Embedded
    private CommonInfo commonInfo;

    @OneToOne(mappedBy = "stop")
    private StopAdditionalInfoEntity additionalInfo;
}
