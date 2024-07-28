package com.example.gamelytics.application;

import com.example.gamelytics.domain.GameItem;
import com.example.gamelytics.domain.GameRepository;

public class GetGameDetails {
    private final GameRepository gameRepository;

    public GetGameDetails(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameItem execute(int id) {
        return gameRepository.get(id);
    }
}

