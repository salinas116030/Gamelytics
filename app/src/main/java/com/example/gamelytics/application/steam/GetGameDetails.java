package com.example.gamelytics.application.steam;

import com.example.gamelytics.domain.steam.GameSteamRepository;
import com.example.gamelytics.domain.steam.GameSteam;

public class GetGameDetails {
    private final GameSteamRepository gameRepository;

    public GetGameDetails(GameSteamRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameSteam execute(String id) {
        return gameRepository.get(id);
    }
}