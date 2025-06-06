package com.pyro_sigth.api.services;

import com.pyro_sigth.api.domains.satellite_image.SatelliteImage;
import com.pyro_sigth.api.dtos.satellite_image.SatelliteImageRequestDTO;
import com.pyro_sigth.api.dtos.satellite_image.SatelliteImageResponseDTO;
import com.pyro_sigth.api.repositories.SatelliteImageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SatelliteImageService {

    private final SatelliteImageRepository satelliteImageRepository;

    public SatelliteImageService(SatelliteImageRepository satelliteImageRepository) {
        this.satelliteImageRepository = satelliteImageRepository;
    }

    private SatelliteImageResponseDTO toResponseDTO(SatelliteImage satelliteImage) {
        SatelliteImageResponseDTO dto = new SatelliteImageResponseDTO();

        dto.setId(satelliteImage.getId());
        dto.setImageType(satelliteImage.getImageType());
        dto.setUrl(satelliteImage.getUrl());
        dto.setLatitude(satelliteImage.getLatitude());
        dto.setLongitude(satelliteImage.getLongitude());
        dto.setCapturedAt(satelliteImage.getCapturedAt());

        return dto;
    }

    public SatelliteImageResponseDTO save(SatelliteImageRequestDTO body) {
        SatelliteImage newSatelliteImage = new SatelliteImage();

        newSatelliteImage.setImageType(body.getImageType());
        newSatelliteImage.setUrl(body.getUrl());
        newSatelliteImage.setLatitude(body.getLatitude());
        newSatelliteImage.setLongitude(body.getLongitude());
        newSatelliteImage.setCapturedAt(body.getCapturedAt());

        SatelliteImage savedSatelliteImage = satelliteImageRepository.save(newSatelliteImage);

        return toResponseDTO(savedSatelliteImage);
    }

    public List<SatelliteImageResponseDTO> findAll() {
        List<SatelliteImage> satelliteImages = satelliteImageRepository.findAll();
        return satelliteImages.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public SatelliteImageResponseDTO findById(UUID id) {
        SatelliteImage satelliteImage = satelliteImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Satellite Image not found"));

        return toResponseDTO(satelliteImage);
    }

    public SatelliteImageResponseDTO update(String id, SatelliteImageRequestDTO body) {
        SatelliteImage satelliteImage = satelliteImageRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Satellite Image not found"));

        satelliteImage.setImageType(body.getImageType());
        satelliteImage.setUrl(body.getUrl());
        satelliteImage.setLatitude(body.getLatitude());
        satelliteImage.setLongitude(body.getLongitude());
        satelliteImage.setCapturedAt(body.getCapturedAt());

        SatelliteImage updatedSatelliteImage = satelliteImageRepository.save(satelliteImage);
        return toResponseDTO(updatedSatelliteImage);
    }

    public void delete(UUID id) {
        SatelliteImage satelliteImage = satelliteImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Satellite Image not found"));
        satelliteImageRepository.delete(satelliteImage);
    }
}
