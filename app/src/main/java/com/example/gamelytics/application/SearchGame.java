package com.example.gamelytics.application;

import com.example.gamelytics.domain.GameItem;
import com.example.gamelytics.domain.GameRepository;
import java.util.List;

public class SearchGame {
    private final GameRepository gameRepository;

    public SearchGame(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameItem> execute(String name) {
        return gameRepository.search(name);
    }
}

