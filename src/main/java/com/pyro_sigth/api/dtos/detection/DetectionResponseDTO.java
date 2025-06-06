package com.pyro_sigth.api.dtos.detection;

import com.pyro_sigth.api.domains.drone.Drone;
import com.pyro_sigth.api.domains.satellite_image.SatelliteImage;
import com.pyro_sigth.api.domains.sensor.Sensor;
import com.pyro_sigth.api.domains.weather_data.WeatherData;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class DetectionResponseDTO {
    private UUID id;
    private Sensor sensor;
    private Drone drone;
    private SatelliteImage satelliteImage;
    private WeatherData weatherData;
    private BigDecimal confidenceLevel;
    private LocalDateTime detectedAt;
}
