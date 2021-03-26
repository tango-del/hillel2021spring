package org.homework.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CommonInfo {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 10_000)
    private String description;
}
