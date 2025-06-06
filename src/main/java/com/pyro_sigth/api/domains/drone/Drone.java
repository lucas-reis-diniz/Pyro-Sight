package com.pyro_sigth.api.domains.drone;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "drone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Drone {

    @Id
    @GeneratedValue
    private UUID id;

    private String model;

    @Column(nullable = false, length = 100)
    private String identifier;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private DroneStatus status;

    private Integer autonomyMinutes;

    private Double lastLatitude;

    private Double lastLongitude;

    public enum DroneStatus {
        ACTIVE,
        MAINTENANCE,
        UNAVAILABLE
    }
}
