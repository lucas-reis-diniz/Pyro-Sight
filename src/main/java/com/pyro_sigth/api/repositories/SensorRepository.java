package com.pyro_sigth.api.repositories;

import com.pyro_sigth.api.domains.sensor.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, UUID> {
}
