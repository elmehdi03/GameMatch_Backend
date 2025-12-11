package com.example.backend_gamematch.repository;

import com.example.backend_gamematch.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByName(String name);
}
