package com.pyro_sigth.api.repositories;

import com.pyro_sigth.api.domains.detection.Detection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DetectionRepository extends JpaRepository<Detection, UUID> {
}
