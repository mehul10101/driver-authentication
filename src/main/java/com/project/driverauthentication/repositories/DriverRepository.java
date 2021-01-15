package com.project.driverauthentication.repositories;

import com.project.driverauthentication.entities.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface DriverRepository extends JpaRepository<DriverEntity, Integer> {

    Optional<DriverEntity> findFirstByUserName(String userName);

    Optional<DriverEntity> findFirstByEmail(String email);
}
