package com.pyro_sigth.api.controllers;

import com.pyro_sigth.api.dtos.drone.DroneRequestDTO;
import com.pyro_sigth.api.dtos.drone.DroneResponseDTO;
import com.pyro_sigth.api.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/drones")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping
    public ResponseEntity<DroneResponseDTO> save(@RequestBody DroneRequestDTO body) {
        DroneResponseDTO drone = this.droneService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(drone);
    }

    @GetMapping
    public ResponseEntity<List<DroneResponseDTO>> findAll() {
        List<DroneResponseDTO> drones = this.droneService.findAll();
        return ResponseEntity.ok(drones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DroneResponseDTO> findById(@PathVariable String id) {
        DroneResponseDTO drone = this.droneService.findById(UUID.fromString(id));

        if (drone == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(drone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DroneResponseDTO> update(@PathVariable String id, @RequestBody DroneRequestDTO body) {
        DroneResponseDTO drone = this.droneService.update(id, body);

        return ResponseEntity.status(HttpStatus.OK).body(drone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        DroneResponseDTO drone = this.droneService.findById(UUID.fromString(id));

        if (drone == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        this.droneService.delete(drone.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
