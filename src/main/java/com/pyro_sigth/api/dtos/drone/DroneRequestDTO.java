package com.pyro_sigth.api.dtos.drone;

import com.pyro_sigth.api.domains.drone.Drone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DroneRequestDTO {
    private String model;
    private String identifier;
    private Drone.DroneStatus status;
    private Integer autonomyMinutes;
    private Double lastLatitude;
    private Double lastLongitude;
}
