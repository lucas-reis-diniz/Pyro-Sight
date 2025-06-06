package com.pyro_sigth.api.controllers;

import com.pyro_sigth.api.dtos.notification.NotificationRequestDTO;
import com.pyro_sigth.api.dtos.notification.NotificationResponseDTO;
import com.pyro_sigth.api.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponseDTO> save(@RequestBody NotificationRequestDTO body) {
        NotificationResponseDTO notification = this.notificationService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> findAll() {
        List<NotificationResponseDTO> notifications = this.notificationService.findAll();
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseDTO> findById(@PathVariable String id) {
        NotificationResponseDTO notification = this.notificationService.findById(UUID.fromString(id));

        if (notification == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(notification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationResponseDTO> update(@PathVariable String id, @RequestBody NotificationRequestDTO body) {
        NotificationResponseDTO notification = this.notificationService.update(id, body);

        return ResponseEntity.status(HttpStatus.OK).body(notification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        NotificationResponseDTO notification = this.notificationService.findById(UUID.fromString(id));

        if (notification == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        this.notificationService.delete(notification.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
