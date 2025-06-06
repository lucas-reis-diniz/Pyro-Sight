package com.pyro_sigth.api.domains.sensor;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "sensor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sensor {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private SensorType type;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(columnDefinition = "TEXT")
    private String locationDescription;

    public enum SensorType {
        TEMPERATURE,
        HUMIDITY,
        PRESSURE,
        LIGHT,
        MOTION
    }
}
