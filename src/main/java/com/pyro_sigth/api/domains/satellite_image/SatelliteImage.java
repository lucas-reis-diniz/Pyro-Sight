package com.pyro_sigth.api.domains.satellite_image;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "satellite_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SatelliteImage {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String url;

    @Column(nullable = false)
    private LocalDateTime capturedAt;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    private Double latitude;

    private Double longitude;

    public enum ImageType {
        VISIBLE,
        INFRARED,
        WATER_VAPOR,
        MULTISPECTRAL,
        THERMAL,
        OPTICAL
    }
}
