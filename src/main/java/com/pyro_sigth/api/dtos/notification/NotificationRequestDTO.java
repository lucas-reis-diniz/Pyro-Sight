package com.pyro_sigth.api.dtos.notification;

import com.pyro_sigth.api.domains.notification.Notification;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class NotificationRequestDTO {
    private UUID userId;
    private UUID alertId;
    private String message;
    private Notification.NotificationStatus status;
    private LocalDateTime sentAt;
}
