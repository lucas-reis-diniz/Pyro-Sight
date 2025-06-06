package com.pyro_sigth.api.services;

import com.pyro_sigth.api.domains.alert.Alert;
import com.pyro_sigth.api.domains.detection.Detection;
import com.pyro_sigth.api.dtos.alert.AlertRequestDTO;
import com.pyro_sigth.api.dtos.alert.AlertResponseDTO;
import com.pyro_sigth.api.repositories.AlertRepository;
import com.pyro_sigth.api.repositories.DetectionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final DetectionRepository detectionRepository;

    public AlertService(AlertRepository alertRepository, DetectionRepository detectionRepository) {
        this.alertRepository = alertRepository;
        this.detectionRepository = detectionRepository;
    }

    private AlertResponseDTO toResponseDTO(Alert alert) {
        AlertResponseDTO dto = new AlertResponseDTO();

        dto.setId(alert.getId());
        dto.setDetection(alert.getDetection());
        dto.setConfirmed(alert.getConfirmed());
        dto.setDangerLevel(alert.getDangerLevel());
        dto.setReportedAt(alert.getReportedAt());

        return dto;
    }

    public AlertResponseDTO save(AlertRequestDTO body) {
        Detection detection = detectionRepository.findById(body.getDetectionId())
                .orElseThrow(() -> new EntityNotFoundException("Detection not found"));

        Alert newAlert = new Alert();

        newAlert.setDetection(detection);
        newAlert.setDangerLevel(body.getDangerLevel());
        newAlert.setConfirmed(body.getConfirmed());
        newAlert.setReportedAt(body.getReportedAt());

        Alert savedAlert = alertRepository.save(newAlert);

        return toResponseDTO(savedAlert);
    }

    public List<AlertResponseDTO> findAll() {
        List<Alert> alerts = alertRepository.findAll();
        return alerts.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public AlertResponseDTO findById(UUID id) {
        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alert not found"));

        return toResponseDTO(alert);
    }

    public AlertResponseDTO update(String id, AlertRequestDTO body) {
        Alert alert = alertRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Alert not found"));

        Detection detection = detectionRepository.findById(body.getDetectionId())
                .orElseThrow(() -> new EntityNotFoundException("Detection not found"));

        alert.setDetection(detection);
        alert.setDangerLevel(body.getDangerLevel());
        alert.setConfirmed(body.getConfirmed());
        alert.setReportedAt(body.getReportedAt());

        Alert updatedAlert = alertRepository.save(alert);
        return toResponseDTO(updatedAlert);
    }

    public void delete(UUID id) {
        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alert not found"));
        alertRepository.delete(alert);
    }
}
