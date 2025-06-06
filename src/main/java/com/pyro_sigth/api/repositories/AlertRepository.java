package com.pyro_sigth.api.repositories;

import com.pyro_sigth.api.domains.alert.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlertRepository extends JpaRepository<Alert, UUID> {
}
