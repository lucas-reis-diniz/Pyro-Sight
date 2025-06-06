package com.pyro_sigth.api.dtos.weather_data;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class WeatherDataResponseDTO {
    private UUID id;
    private BigDecimal temperatureCelsius;
    private BigDecimal humidityPercent;
    private BigDecimal windSpeedKmh;
    private BigDecimal pressureHpa;
    private LocalDateTime recordedAt;
}
