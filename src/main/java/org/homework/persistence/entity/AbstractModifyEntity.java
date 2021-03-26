package org.homework.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.homework.persistence.entity.util.YesNoConverter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractModifyEntity<ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @Column(name = "create_date")
    @CreationTimestamp
    private Instant createDate;

    @Column(name = "active")
    //@Type(type = "true_false")
    //@Type(type = "yes_no")
//    @Type(type = "numeric_boolean")
//    @Type(type = "boolean")
//    @Type(type = "true_false")
    @Convert(converter = YesNoConverter.class)
    private boolean active = true;
}
