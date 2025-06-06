package com.pyro_sigth.api.domains.alert;

import com.pyro_sigth.api.domains.detection.Detection;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alert")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Alert {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "detection_id")
    private Detection detection;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private DangerLevel dangerLevel;

    private Boolean confirmed = false;

    @Column(nullable = false)
    private LocalDateTime reportedAt;

    public enum DangerLevel {
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL
    }
}

