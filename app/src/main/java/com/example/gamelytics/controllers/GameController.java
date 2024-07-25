package com.example.gamelytics.controllers;

import com.example.gamelytics.application.GetGameDetails;
import com.example.gamelytics.application.SearchGame;
import com.example.gamelytics.domain.Game;
import com.example.gamelytics.domain.GameRepository;
import java.util.List;

public class GameController {
    private final GameRepository gameRepository;
    private final SearchGame UseCaseSearchGame;
    private final GetGameDetails UseCaseGetGameDetails;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.UseCaseSearchGame = new SearchGame(gameRepository);
        this.UseCaseGetGameDetails = new GetGameDetails(gameRepository);
    }

    public Game getGame(int id) {
        return UseCaseGetGameDetails.execute(id);
    }

    public List<Game> searchAllGames(String name) {
        return UseCaseSearchGame.execute(name);
    }
}

