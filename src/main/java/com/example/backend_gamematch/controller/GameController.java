package com.example.backend_gamematch.controller;

import com.example.backend_gamematch.model.Game;
import com.example.backend_gamematch.repository.GameRepository;
import com.example.backend_gamematch.exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        if (gameRepository.findByName(game.getName()).isPresent()) {
            throw new BadRequestException("Game already exists");
        }
        return ResponseEntity.ok(gameRepository.save(game));
    }
}
