package com.pyro_sigth.api.dtos.alert;

import com.pyro_sigth.api.domains.alert.Alert;
import com.pyro_sigth.api.domains.detection.Detection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class AlertResponseDTO {
    private UUID id;
    private Detection detection;
    private Alert.DangerLevel dangerLevel;
    private Boolean confirmed;
    private LocalDateTime reportedAt;
}
