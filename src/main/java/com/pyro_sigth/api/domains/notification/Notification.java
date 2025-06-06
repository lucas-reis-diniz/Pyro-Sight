package com.pyro_sigth.api.domains.notification;

import com.pyro_sigth.api.domains.alert.Alert;
import com.pyro_sigth.api.domains.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notification {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "alert_id")
    private Alert alert;

    private LocalDateTime sentAt;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    public enum NotificationStatus {
        PENDING,
        SENT,
        FAILED,
        READ
    }
}

