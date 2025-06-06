package com.pyro_sigth.api.repositories;

import com.pyro_sigth.api.domains.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
