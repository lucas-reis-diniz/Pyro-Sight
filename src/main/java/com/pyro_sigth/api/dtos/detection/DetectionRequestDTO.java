package com.pyro_sigth.api.dtos.detection;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class DetectionRequestDTO {
    private UUID sensorId;
    private UUID droneId;
    private UUID satelliteImageId;
    private UUID weatherDataId;
    private BigDecimal confidenceLevel;
    private LocalDateTime detectedAt;
}
