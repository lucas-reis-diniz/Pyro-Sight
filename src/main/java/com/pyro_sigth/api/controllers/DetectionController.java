package com.pyro_sigth.api.controllers;

import com.pyro_sigth.api.dtos.detection.DetectionRequestDTO;
import com.pyro_sigth.api.dtos.detection.DetectionResponseDTO;
import com.pyro_sigth.api.services.DetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/detections")
public class DetectionController {

    @Autowired
    private DetectionService detectionService;

    @PostMapping
    public ResponseEntity<DetectionResponseDTO> save(@RequestBody DetectionRequestDTO body) {
        DetectionResponseDTO detection = this.detectionService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(detection);
    }

    @GetMapping
    public ResponseEntity<List<DetectionResponseDTO>> findAll() {
        List<DetectionResponseDTO> detections = this.detectionService.findAll();
        return ResponseEntity.ok(detections);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetectionResponseDTO> findById(@PathVariable String id) {
        DetectionResponseDTO detection = this.detectionService.findById(UUID.fromString(id));

        if (detection == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(detection);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetectionResponseDTO> update(@PathVariable String id, @RequestBody DetectionRequestDTO body) {
        DetectionResponseDTO detection = this.detectionService.update(id, body);

        return ResponseEntity.status(HttpStatus.OK).body(detection);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        DetectionResponseDTO detection = this.detectionService.findById(UUID.fromString(id));

        if (detection == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        this.detectionService.delete(detection.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
