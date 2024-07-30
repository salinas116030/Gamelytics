package com.example.gamelytics.infrastructure.internal.controllers.steam;

import com.example.gamelytics.application.steam.GetGameDetails;
import com.example.gamelytics.domain.steam.GameSteamRepository;
import com.example.gamelytics.domain.steam.GameSteam;

public class GameSteamController {
    private final GameSteamRepository gameSteamRepository;
    private final GetGameDetails UseCaseGetGameDetails;

    public GameSteamController(GameSteamRepository gameSteamRepository) {
        this.gameSteamRepository = gameSteamRepository;
        this.UseCaseGetGameDetails = new GetGameDetails(gameSteamRepository);
    }

    public GameSteam getGame(String id) {
        return UseCaseGetGameDetails.execute(id);
    }

}
