package com.pyro_sigth.api.dtos.drone;

import com.pyro_sigth.api.domains.drone.Drone;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DroneResponseDTO {
    private UUID id;
    private String model;
    private String identifier;
    private Drone.DroneStatus status;
    private Integer autonomyMinutes;
    private Double lastLatitude;
    private Double lastLongitude;
}
