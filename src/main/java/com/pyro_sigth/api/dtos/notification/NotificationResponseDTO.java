package com.pyro_sigth.api.dtos.notification;

import com.pyro_sigth.api.domains.alert.Alert;
import com.pyro_sigth.api.domains.notification.Notification;
import com.pyro_sigth.api.domains.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class NotificationResponseDTO {
    private UUID id;
    private User user;
    private Alert alert;
    private String message;
    private Notification.NotificationStatus status;
    private LocalDateTime sentAt;
}
