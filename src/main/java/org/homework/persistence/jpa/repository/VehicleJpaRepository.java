package org.homework.persistence.jpa.repository;

import org.homework.persistence.entity.VehicleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

//public interface VehicleJpaRepository extends CommonJpaRepository<VehicleEntity, Long>, JpaRepository<VehicleEntity, Long> {
public interface VehicleJpaRepository extends CommonJpaRepository<VehicleEntity, Long> {

    Collection<VehicleEntity> findByVehicleName(String name);

    Collection<VehicleEntity> findDistinctFirst3ByVehicleName(String name);

    @Query("select v from VehicleEntity v where v.id between :id_from and :id_to and v.vehicleName = :param_name")
    List<VehicleEntity> findByConditions(@Param("param_name") String name,
                                         @Param("id_from") Long idFrom,
                                         @Param("id_to") Long odTo,
                                         Pageable page);

    @Query(value = "select v from VehicleEntity v where v.id between :id_from and :id_to and v.vehicleName = :param_name",
    countQuery = "select count(v) from VehicleEntity v where v.id between :id_from and :id_to and v.vehicleName = :param_name")
    Page<VehicleEntity> findByConditionsParam(@Param("param_name") String name,
                                              @Param("id_from") Long idFrom,
                                              @Param("id_to") Long odTo,
                                              Pageable page);

    @Query(value = "select v.* from vehicle v where v.id between :id_from and :id_to and v.name = :param_name",
    countQuery = "select count(v.id) from vehicle v", nativeQuery = true)
    Page<VehicleEntity> findByConditionsParamNative(@Param("param_name") String name,
                                                    @Param("id_from") Long idFrom,
                                                    @Param("id_to") Long idTo, Pageable page);
}
