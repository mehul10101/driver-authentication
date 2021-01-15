package com.project.driverauthentication.repositories;

import com.project.driverauthentication.entities.VehicleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleTypeRepository extends JpaRepository<VehicleTypeEntity, Integer> {

    Optional<VehicleTypeEntity> findByVehicleType(String vehicleType);
}
