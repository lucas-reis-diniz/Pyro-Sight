package com.pyro_sigth.api.dtos.sensor;

import com.pyro_sigth.api.domains.sensor.Sensor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SensorResponseDTO {
    private UUID id;
    private Sensor.SensorType type;
    private Double latitude;
    private Double longitude;
    private String locationDescription;
}
