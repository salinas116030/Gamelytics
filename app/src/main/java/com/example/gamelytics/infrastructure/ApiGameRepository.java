package com.example.gamelytics.infrastructure;

import com.example.gamelytics.domain.Game;
import com.example.gamelytics.domain.GameItem;
import com.example.gamelytics.domain.GameRepository;
import com.example.gamelytics.infrastructure.external.api.SharkApiClient;
import com.example.gamelytics.infrastructure.external.api.services.Shark.GameApiService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.List;

public class ApiGameRepository implements GameRepository {
    private final GameApiService gameApiService;

    public ApiGameRepository() {
        Retrofit retrofit = SharkApiClient.getClient();
        gameApiService = retrofit.create(GameApiService.class);
    }

    @Override
    public Game get(int id) {
        Call<Game> call = gameApiService.getGame(id);
        try {
            Response<Game> response = call.execute();
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<GameItem> search(String name) {
        Call<List<GameItem>> call = gameApiService.searchGames(name);
        try {
            Response<List<GameItem>> response = call.execute();
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
