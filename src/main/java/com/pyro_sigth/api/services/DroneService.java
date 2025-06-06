package com.pyro_sigth.api.services;

import com.pyro_sigth.api.domains.drone.Drone;
import com.pyro_sigth.api.dtos.drone.DroneRequestDTO;
import com.pyro_sigth.api.dtos.drone.DroneResponseDTO;
import com.pyro_sigth.api.repositories.DroneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DroneService {

    private final DroneRepository droneRepository;

    public DroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    private DroneResponseDTO toResponseDTO(Drone drone) {
        DroneResponseDTO dto = new DroneResponseDTO();

        dto.setId(drone.getId());
        dto.setIdentifier(drone.getIdentifier());
        dto.setModel(drone.getModel());
        dto.setStatus(drone.getStatus());
        dto.setAutonomyMinutes(drone.getAutonomyMinutes());
        dto.setLastLatitude(drone.getLastLatitude());
        dto.setLastLongitude(drone.getLastLongitude());

        return dto;
    }

    public DroneResponseDTO save(DroneRequestDTO body) {
        Drone newDrone = new Drone();

        newDrone.setIdentifier(body.getIdentifier());
        newDrone.setStatus(body.getStatus());
        newDrone.setModel(body.getModel());
        newDrone.setAutonomyMinutes(body.getAutonomyMinutes());
        newDrone.setLastLatitude(body.getLastLatitude());
        newDrone.setLastLongitude(body.getLastLongitude());

        Drone savedDrone = droneRepository.save(newDrone);

        return toResponseDTO(savedDrone);
    }

    public List<DroneResponseDTO> findAll() {
        List<Drone> drones = droneRepository.findAll();
        return drones.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public DroneResponseDTO findById(UUID id) {
        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Drone not found"));

        return toResponseDTO(drone);
    }

    public DroneResponseDTO update(String id, DroneRequestDTO body) {
        Drone drone = droneRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Drone not found"));

        drone.setIdentifier(body.getIdentifier());
        drone.setStatus(body.getStatus());
        drone.setModel(body.getModel());
        drone.setAutonomyMinutes(body.getAutonomyMinutes());
        drone.setLastLatitude(body.getLastLatitude());
        drone.setLastLongitude(body.getLastLongitude());

        Drone updatedDrone = droneRepository.save(drone);
        return toResponseDTO(updatedDrone);
    }

    public void delete(UUID id) {
        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Drone not found"));
        droneRepository.delete(drone);
    }
}

