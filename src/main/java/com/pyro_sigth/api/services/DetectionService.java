package com.pyro_sigth.api.services;

import com.pyro_sigth.api.domains.detection.Detection;
import com.pyro_sigth.api.domains.drone.Drone;
import com.pyro_sigth.api.domains.satellite_image.SatelliteImage;
import com.pyro_sigth.api.domains.sensor.Sensor;
import com.pyro_sigth.api.domains.weather_data.WeatherData;
import com.pyro_sigth.api.dtos.detection.DetectionRequestDTO;
import com.pyro_sigth.api.dtos.detection.DetectionResponseDTO;
import com.pyro_sigth.api.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DetectionService {

    private final DetectionRepository detectionRepository;
    private final DroneRepository droneRepository;
    private final SensorRepository sensorRepository;
    private final SatelliteImageRepository satelliteImageRepository;
    private final WeatherDataRepository weatherDataRepository;

    public DetectionService(DetectionRepository detectionRepository,
                            DroneRepository droneRepository,
                            SensorRepository sensorRepository,
                            SatelliteImageRepository satelliteImageRepository,
                            WeatherDataRepository weatherDataRepository) {
        this.detectionRepository = detectionRepository;
        this.droneRepository = droneRepository;
        this.sensorRepository = sensorRepository;
        this.satelliteImageRepository = satelliteImageRepository;
        this.weatherDataRepository = weatherDataRepository;
    }

    private DetectionResponseDTO toResponseDTO(Detection detection) {
        DetectionResponseDTO dto = new DetectionResponseDTO();

        dto.setId(detection.getId());
        dto.setDrone(detection.getDrone());
        dto.setSensor(detection.getSensor());
        dto.setSatelliteImage(detection.getSatelliteImage());
        dto.setWeatherData(detection.getWeatherData());
        dto.setConfidenceLevel(detection.getConfidenceLevel());
        dto.setDetectedAt(detection.getDetectedAt());

        return dto;
    }

    public DetectionResponseDTO save(DetectionRequestDTO body) {
        Drone drone = droneRepository.findById(body.getDroneId())
                .orElseThrow(() -> new EntityNotFoundException("Image not found"));

        Sensor sensor = sensorRepository.findById(body.getSensorId())
                .orElseThrow(() -> new EntityNotFoundException("Sensor not found"));

        SatelliteImage satelliteImage = satelliteImageRepository.findById(body.getSatelliteImageId())
                .orElseThrow(() -> new EntityNotFoundException("Satellite Image not found"));

        WeatherData weatherData = weatherDataRepository.findById(body.getWeatherDataId())
                .orElseThrow(() -> new EntityNotFoundException("Weather Data not found"));

        Detection newDetection = new Detection();

        newDetection.setDrone(drone);
        newDetection.setSensor(sensor);
        newDetection.setSatelliteImage(satelliteImage);
        newDetection.setWeatherData(weatherData);
        newDetection.setConfidenceLevel(body.getConfidenceLevel());
        newDetection.setDetectedAt(body.getDetectedAt());

        Detection savedDetection = detectionRepository.save(newDetection);

        return toResponseDTO(savedDetection);
    }

    public List<DetectionResponseDTO> findAll() {
        List<Detection> detections = detectionRepository.findAll();
        return detections.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public DetectionResponseDTO findById(UUID id) {
        Detection detection = detectionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Detection not found"));

        return toResponseDTO(detection);
    }

    public DetectionResponseDTO update(String id, DetectionRequestDTO body) {
        Detection detection = detectionRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Detection not found"));

        Drone drone = droneRepository.findById(body.getDroneId())
                .orElseThrow(() -> new EntityNotFoundException("Image not found"));

        Sensor sensor = sensorRepository.findById(body.getSensorId())
                .orElseThrow(() -> new EntityNotFoundException("Sensor not found"));

        SatelliteImage satelliteImage = satelliteImageRepository.findById(body.getSatelliteImageId())
                .orElseThrow(() -> new EntityNotFoundException("Satellite Image not found"));

        WeatherData weatherData = weatherDataRepository.findById(body.getWeatherDataId())
                .orElseThrow(() -> new EntityNotFoundException("Weather Data not found"));

        detection.setDrone(drone);
        detection.setSensor(sensor);
        detection.setSatelliteImage(satelliteImage);
        detection.setWeatherData(weatherData);
        detection.setConfidenceLevel(body.getConfidenceLevel());
        detection.setDetectedAt(body.getDetectedAt());

        Detection updatedDetection = detectionRepository.save(detection);
        return toResponseDTO(updatedDetection);
    }

    public void delete(UUID id) {
        Detection detection = detectionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Detection not found"));
        detectionRepository.delete(detection);
    }
}
