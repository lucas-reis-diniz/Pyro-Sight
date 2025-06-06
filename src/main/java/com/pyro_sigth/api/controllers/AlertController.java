package com.pyro_sigth.api.controllers;

import com.pyro_sigth.api.dtos.alert.AlertRequestDTO;
import com.pyro_sigth.api.dtos.alert.AlertResponseDTO;
import com.pyro_sigth.api.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping
    public ResponseEntity<AlertResponseDTO> save(@RequestBody AlertRequestDTO body) {
        AlertResponseDTO alert = this.alertService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(alert);
    }

    @GetMapping
    public ResponseEntity<List<AlertResponseDTO>> findAll() {
        List<AlertResponseDTO> alerts = this.alertService.findAll();
        return ResponseEntity.ok(alerts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertResponseDTO> findById(@PathVariable String id) {
        AlertResponseDTO alert = this.alertService.findById(UUID.fromString(id));

        if (alert == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(alert);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertResponseDTO> update(@PathVariable String id, @RequestBody AlertRequestDTO body) {
        AlertResponseDTO alert = this.alertService.update(id, body);

        return ResponseEntity.status(HttpStatus.OK).body(alert);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        AlertResponseDTO alert = this.alertService.findById(UUID.fromString(id));

        if (alert == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        this.alertService.delete(alert.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
