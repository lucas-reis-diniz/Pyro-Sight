package com.pyro_sigth.api.repositories;

import com.pyro_sigth.api.domains.weather_data.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WeatherDataRepository extends JpaRepository<WeatherData, UUID> {
}
