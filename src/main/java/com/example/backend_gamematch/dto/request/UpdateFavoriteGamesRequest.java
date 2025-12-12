package com.example.backend_gamematch.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UpdateFavoriteGamesRequest {

    @NotEmpty(message = "Game IDs list cannot be empty")
    private List<Long> gameIds;
}

