package com.pyro_sigth.api.dtos.sensor;

import com.pyro_sigth.api.domains.sensor.Sensor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorRequestDTO {
    private Sensor.SensorType type;
    private Double latitude;
    private Double longitude;
    private String locationDescription;
}
