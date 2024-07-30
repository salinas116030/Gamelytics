package com.example.gamelytics.infrastructure;

import com.example.gamelytics.domain.steam.GameInfo;
import com.example.gamelytics.domain.steam.GameSteam;
import com.example.gamelytics.domain.steam.GameSteamRepository;
import com.example.gamelytics.infrastructure.external.api.SteamApiClient;
import com.example.gamelytics.infrastructure.external.api.services.Steam.GameApiService;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiGameSteamRepository implements GameSteamRepository {
    private final GameApiService gameApiService;

    public ApiGameSteamRepository() {
        Retrofit retrofit = SteamApiClient.getClient();
        gameApiService = retrofit.create(GameApiService.class);
    }

    @Override
    public GameSteam get(String id) {
        Call<GameSteam> call = gameApiService.getGame(id);
        try {
            Response<GameSteam> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                GameSteam gameSteam = response.body();
                return gameSteam;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
