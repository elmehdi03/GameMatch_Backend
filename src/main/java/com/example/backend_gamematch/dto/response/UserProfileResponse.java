package com.example.backend_gamematch.dto.response;

import com.example.backend_gamematch.model.Game;
import com.example.backend_gamematch.model.User;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class UserProfileResponse {
    private Long id;
    private String username;
    private String email;
    private String fullname;
    private String city;
    private String bio;
    private String playstyle;
    private String gamemode;
    private String discordId;
    private LocalDateTime createdAt;
    private Set<GameInfo> favoriteGames;

    public static UserProfileResponse fromUser(User user) {
        UserProfileResponse response = new UserProfileResponse();
        response.id = user.getId();
        response.username = user.getUsername();
        response.email = user.getEmail();
        response.fullname = user.getFullname();
        response.city = user.getCity();
        response.bio = user.getBio();
        response.playstyle = user.getPlaystyle();
        response.gamemode = user.getGamemode();
        response.discordId = user.getDiscordId();
        response.createdAt = user.getCreatedAt();
        response.favoriteGames = user.getFavoriteGames().stream()
                .map(GameInfo::fromGame)
                .collect(Collectors.toSet());
        return response;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPlaystyle() {
        return playstyle;
    }

    public void setPlaystyle(String playstyle) {
        this.playstyle = playstyle;
    }

    public String getGamemode() {
        return gamemode;
    }

    public void setGamemode(String gamemode) {
        this.gamemode = gamemode;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<GameInfo> getFavoriteGames() {
        return favoriteGames;
    }

    public void setFavoriteGames(Set<GameInfo> favoriteGames) {
        this.favoriteGames = favoriteGames;
    }

    public static class GameInfo {
        private Long id;
        private String name;

        public static GameInfo fromGame(Game game) {
            GameInfo info = new GameInfo();
            info.id = game.getId();
            info.name = game.getName();
            return info;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

