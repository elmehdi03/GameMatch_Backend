package com.example.backend_gamematch.controller;

import com.example.backend_gamematch.dto.request.UpdateFavoriteGamesRequest;
import com.example.backend_gamematch.model.Game;
import com.example.backend_gamematch.model.User;
import com.example.backend_gamematch.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/favorite-games")
    public ResponseEntity<Set<Game>> getFavoriteGames(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getFavoriteGames(userId));
    }

    @PostMapping("/{userId}/favorite-games")
    public ResponseEntity<User> addFavoriteGames(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateFavoriteGamesRequest request) {
        return ResponseEntity.ok(userService.addFavoriteGames(userId, request.getGameIds()));
    }

    @PutMapping("/{userId}/favorite-games")
    public ResponseEntity<User> setFavoriteGames(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateFavoriteGamesRequest request) {
        return ResponseEntity.ok(userService.setFavoriteGames(userId, request.getGameIds()));
    }

    @DeleteMapping("/{userId}/favorite-games")
    public ResponseEntity<User> removeFavoriteGames(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateFavoriteGamesRequest request) {
        return ResponseEntity.ok(userService.removeFavoriteGames(userId, request.getGameIds()));
    }
}

