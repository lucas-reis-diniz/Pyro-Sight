package com.pyro_sigth.api.domains.detection;

import com.pyro_sigth.api.domains.drone.Drone;
import com.pyro_sigth.api.domains.satellite_image.SatelliteImage;
import com.pyro_sigth.api.domains.sensor.Sensor;
import com.pyro_sigth.api.domains.weather_data.WeatherData;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "detection")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Detection {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;

    @ManyToOne
    @JoinColumn(name = "satellite_image_id")
    private SatelliteImage satelliteImage;

    @ManyToOne
    @JoinColumn(name = "weather_data_id")
    private WeatherData weatherData;

    private BigDecimal confidenceLevel;

    @Column(nullable = false)
    private LocalDateTime detectedAt;
}
