package com.example.backend_gamematch.service;


import com.example.backend_gamematch.dto.request.LoginRequest;
import com.example.backend_gamematch.dto.request.RegisterRequest;
import com.example.backend_gamematch.dto.response.AuthResponse;
import com.example.backend_gamematch.model.User;
import com.example.backend_gamematch.repository.UserRepository;
import com.example.backend_gamematch.exception.BadRequestException;
import com.example.backend_gamematch.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // à configurer

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already in use");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullname(request.getFullname())
                .city(request.getCity())
                .playstyle(request.getPlaystyle())
                .gamemode(request.getGamemode())
                .bio(request.getBio())
                .discordId(request.getDiscordId())
                .build();

        userRepository.save(user);

        // v1 : token dummy, on branchera JWT plus tard
        return new AuthResponse("DUMMY_TOKEN", user.getId(), user.getUsername());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsernameOrEmail())
                .or(() -> userRepository.findByEmail(request.getUsernameOrEmail()))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        return new AuthResponse("DUMMY_TOKEN", user.getId(), user.getUsername());
    }
}
