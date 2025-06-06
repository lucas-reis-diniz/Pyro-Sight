package com.pyro_sigth.api.services;

import com.pyro_sigth.api.domains.sensor.Sensor;
import com.pyro_sigth.api.dtos.sensor.SensorRequestDTO;
import com.pyro_sigth.api.dtos.sensor.SensorResponseDTO;
import com.pyro_sigth.api.repositories.SensorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    private SensorResponseDTO toResponseDTO(Sensor sensor) {
        SensorResponseDTO dto = new SensorResponseDTO();

        dto.setId(sensor.getId());
        dto.setType(sensor.getType());
        dto.setLatitude(sensor.getLatitude());
        dto.setLongitude(sensor.getLongitude());
        dto.setLocationDescription(sensor.getLocationDescription());

        return dto;
    }

    public SensorResponseDTO save(SensorRequestDTO body) {
        Sensor newSensor = new Sensor();

        newSensor.setType(body.getType());
        newSensor.setLatitude(body.getLatitude());
        newSensor.setLongitude(body.getLongitude());
        newSensor.setLocationDescription(body.getLocationDescription());

        Sensor savedSensor = sensorRepository.save(newSensor);

        return toResponseDTO(savedSensor);
    }

    public List<SensorResponseDTO> findAll() {
        List<Sensor> sensors = sensorRepository.findAll();
        return sensors.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public SensorResponseDTO findById(UUID id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sensor not found"));

        return toResponseDTO(sensor);
    }

    public SensorResponseDTO update(String id, SensorRequestDTO body) {
        Sensor sensor = sensorRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Sensor not found"));

        sensor.setType(body.getType());
        sensor.setLatitude(body.getLatitude());
        sensor.setLongitude(body.getLongitude());
        sensor.setLocationDescription(body.getLocationDescription());

        Sensor updatedSensor = sensorRepository.save(sensor);
        return toResponseDTO(updatedSensor);
    }

    public void delete(UUID id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sensor not found"));
        sensorRepository.delete(sensor);
    }
}

