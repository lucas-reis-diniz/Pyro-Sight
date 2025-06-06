package com.pyro_sigth.api.dtos.satellite_image;

import com.pyro_sigth.api.domains.satellite_image.SatelliteImage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SatelliteImageRequestDTO {
    private String url;
    private LocalDateTime capturedAt;
    private SatelliteImage.ImageType imageType;
    private Double latitude;
    private Double longitude;
}
