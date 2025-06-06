package com.pyro_sigth.api.controllers;

import com.pyro_sigth.api.domains.sensor.Sensor;
import com.pyro_sigth.api.dtos.sensor.SensorRequestDTO;
import com.pyro_sigth.api.dtos.sensor.SensorResponseDTO;
import com.pyro_sigth.api.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sensors")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @PostMapping
    public ResponseEntity<SensorResponseDTO> save(@RequestBody SensorRequestDTO body) {
        SensorResponseDTO sensor = this.sensorService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(sensor);
    }

    @GetMapping
    public ResponseEntity<List<SensorResponseDTO>> findAll() {
        List<SensorResponseDTO> sensors = this.sensorService.findAll();
        return ResponseEntity.ok(sensors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorResponseDTO> findById(@PathVariable String id) {
        SensorResponseDTO sensor = this.sensorService.findById(UUID.fromString(id));

        if (sensor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(sensor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SensorResponseDTO> update(@PathVariable String id, @RequestBody SensorRequestDTO body) {
        SensorResponseDTO sensor = this.sensorService.update(id, body);

        return ResponseEntity.status(HttpStatus.OK).body(sensor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        SensorResponseDTO sensor = this.sensorService.findById(UUID.fromString(id));

        if (sensor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        this.sensorService.delete(sensor.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
