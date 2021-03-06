package org.homework.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.homework.persistence.entity.util.YesNoConverter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractModifyEntity<ID extends Serializable> implements Persistable<ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @Column(name = "create_date")
    @CreationTimestamp
    private Instant createDate;

    //@Type(type = "true_false")
    //@Type(type = "yes_no")
//    @Type(type = "numeric_boolean")
//    @Type(type = "boolean")
//    @Type(type = "true_false")
    @Column(name = "active")
    @Convert(converter = YesNoConverter.class)
    private boolean active = true;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
