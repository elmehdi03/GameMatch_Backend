package com.example.backend_gamematch.service;

import com.example.backend_gamematch.exception.ResourceNotFoundException;
import com.example.backend_gamematch.model.Game;
import com.example.backend_gamematch.model.User;
import com.example.backend_gamematch.repository.GameRepository;
import com.example.backend_gamematch.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public UserService(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Transactional
    public User addFavoriteGames(Long userId, List<Long> gameIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Game> games = gameRepository.findAllById(gameIds);
        if (games.size() != gameIds.size()) {
            throw new ResourceNotFoundException("One or more games not found");
        }

        user.getFavoriteGames().addAll(games);
        return userRepository.save(user);
    }

    @Transactional
    public User setFavoriteGames(Long userId, List<Long> gameIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Game> games = gameRepository.findAllById(gameIds);
        if (games.size() != gameIds.size()) {
            throw new ResourceNotFoundException("One or more games not found");
        }

        user.getFavoriteGames().clear();
        user.getFavoriteGames().addAll(games);
        return userRepository.save(user);
    }

    @Transactional
    public User removeFavoriteGames(Long userId, List<Long> gameIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Set<Long> gameIdsToRemove = gameIds.stream().collect(Collectors.toSet());
        user.getFavoriteGames().removeIf(game -> gameIdsToRemove.contains(game.getId()));

        return userRepository.save(user);
    }

    public Set<Game> getFavoriteGames(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return user.getFavoriteGames();
    }
}

