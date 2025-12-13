package com.example.backend_gamematch.controller;

import com.example.backend_gamematch.dto.request.UpdateFavoriteGamesRequest;
import com.example.backend_gamematch.dto.request.UpdateProfileRequest;
import com.example.backend_gamematch.dto.response.UserProfileResponse;
import com.example.backend_gamematch.model.Game;
import com.example.backend_gamematch.model.User;
import com.example.backend_gamematch.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> getUserById(@PathVariable Long userId) {
        log.info("Getting user by ID: {}", userId);
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(UserProfileResponse.fromUser(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> updateProfile(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateProfileRequest request) {
        log.info("Updating profile for user ID: {}", userId);
        log.debug("Update request: fullname={}, bio={}, city={}, discordId={}, playstyle={}, gamemode={}",
                request.getFullname(), request.getBio(), request.getCity(),
                request.getDiscordId(), request.getPlaystyle(), request.getGamemode());

        try {
            User user = userService.updateProfile(userId, request);
            log.info("Profile updated successfully for user ID: {}", userId);
            return ResponseEntity.ok(UserProfileResponse.fromUser(user));
        } catch (Exception e) {
            log.error("Error updating profile for user ID: {}", userId, e);
            throw e;
        }
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

