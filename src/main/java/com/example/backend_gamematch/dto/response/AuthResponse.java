package com.example.backend_gamematch.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class AuthResponse {

    private String token;  // pour l’instant on renvoie une chaîne dummy
    private Long userId;
    private String username;
}
