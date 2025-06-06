package com.pyro_sigth.api.services;

import com.pyro_sigth.api.domains.alert.Alert;
import com.pyro_sigth.api.domains.notification.Notification;
import com.pyro_sigth.api.domains.user.User;
import com.pyro_sigth.api.dtos.notification.NotificationRequestDTO;
import com.pyro_sigth.api.dtos.notification.NotificationResponseDTO;
import com.pyro_sigth.api.repositories.AlertRepository;
import com.pyro_sigth.api.repositories.NotificationRepository;
import com.pyro_sigth.api.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final AlertRepository alertRepository;

    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository, AlertRepository alertRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.alertRepository = alertRepository;
    }

    private NotificationResponseDTO toResponseDTO(Notification notification) {
        NotificationResponseDTO dto = new NotificationResponseDTO();

        dto.setId(notification.getId());
        dto.setUser(notification.getUser());
        dto.setAlert(notification.getAlert());
        dto.setStatus(notification.getStatus());
        dto.setSentAt(notification.getSentAt());
        dto.setMessage(notification.getMessage());

        return dto;
    }

    public NotificationResponseDTO save(NotificationRequestDTO body) {
        User user = userRepository.findById(body.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Alert alert = alertRepository.findById(body.getAlertId())
                .orElseThrow(() -> new EntityNotFoundException("Alert not found"));

        Notification newNotification = new Notification();

        newNotification.setUser(user);
        newNotification.setAlert(alert);
        newNotification.setStatus(Notification.NotificationStatus.PENDING);
        newNotification.setSentAt(body.getSentAt());
        newNotification.setMessage(body.getMessage());

        Notification savedNotification = notificationRepository.save(newNotification);

        return toResponseDTO(savedNotification);
    }

    public List<NotificationResponseDTO> findAll() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public NotificationResponseDTO findById(UUID id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found"));

        return toResponseDTO(notification);
    }

    public NotificationResponseDTO update(String id, NotificationRequestDTO body) {
        Notification notification = notificationRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Notification not found"));

        User user = userRepository.findById(body.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Alert alert = alertRepository.findById(body.getAlertId())
                .orElseThrow(() -> new EntityNotFoundException("Alert not found"));

        notification.setUser(user);
        notification.setAlert(alert);
        notification.setStatus(body.getStatus());
        notification.setSentAt(body.getSentAt());
        notification.setMessage(body.getMessage());

        Notification updatedNotification = notificationRepository.save(notification);
        return toResponseDTO(updatedNotification);
    }

    public void delete(UUID id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found"));
        notificationRepository.delete(notification);
    }
}
