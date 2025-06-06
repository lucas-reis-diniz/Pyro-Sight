package com.pyro_sigth.api.repositories;

import com.pyro_sigth.api.domains.drone.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DroneRepository extends JpaRepository<Drone, UUID> {
}
