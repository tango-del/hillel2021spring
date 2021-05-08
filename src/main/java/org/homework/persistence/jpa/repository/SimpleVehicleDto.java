package org.homework.persistence.jpa.repository;

public interface SimpleVehicleDto {

    Long getId();
    String getVehicleName();
    boolean isActive();

    default void toStrings() {
        System.out.println("id = " + getId() + " name = " + getVehicleName() + " active = " + isActive());
    }
}
