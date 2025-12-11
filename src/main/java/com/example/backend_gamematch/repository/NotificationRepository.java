package com.example.backend_gamematch.repository;

import com.example.backend_gamematch.model.Notification;
import com.example.backend_gamematch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser(User user);
}
