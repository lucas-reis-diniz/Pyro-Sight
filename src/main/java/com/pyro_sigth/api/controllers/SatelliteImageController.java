package com.pyro_sigth.api.controllers;

import com.pyro_sigth.api.dtos.satellite_image.SatelliteImageRequestDTO;
import com.pyro_sigth.api.dtos.satellite_image.SatelliteImageResponseDTO;
import com.pyro_sigth.api.services.SatelliteImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/satellite-images")
public class SatelliteImageController {

    @Autowired
    private SatelliteImageService satelliteImageService;

    @PostMapping
    public ResponseEntity<SatelliteImageResponseDTO> save(@RequestBody SatelliteImageRequestDTO body) {
        SatelliteImageResponseDTO satelliteImage = this.satelliteImageService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(satelliteImage);
    }

    @GetMapping
    public ResponseEntity<List<SatelliteImageResponseDTO>> findAll() {
        List<SatelliteImageResponseDTO> satelliteImages = this.satelliteImageService.findAll();
        return ResponseEntity.ok(satelliteImages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SatelliteImageResponseDTO> findById(@PathVariable String id) {
        SatelliteImageResponseDTO satelliteImage = this.satelliteImageService.findById(UUID.fromString(id));

        if (satelliteImage == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(satelliteImage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SatelliteImageResponseDTO> update(@PathVariable String id, @RequestBody SatelliteImageRequestDTO body) {
        SatelliteImageResponseDTO satelliteImage = this.satelliteImageService.update(id, body);

        return ResponseEntity.status(HttpStatus.OK).body(satelliteImage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        SatelliteImageResponseDTO satelliteImage = this.satelliteImageService.findById(UUID.fromString(id));

        if (satelliteImage == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        this.satelliteImageService.delete(satelliteImage.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
