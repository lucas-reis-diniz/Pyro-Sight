package com.pyro_sigth.api.domains.weather_data;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "weather_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeatherData {

    @Id
    @GeneratedValue
    private UUID id;

    private BigDecimal temperatureCelsius;

    private BigDecimal humidityPercent;

    private BigDecimal windSpeedKmh;

    private BigDecimal pressureHpa;

    @Column(nullable = false)
    private LocalDateTime recordedAt;
}
