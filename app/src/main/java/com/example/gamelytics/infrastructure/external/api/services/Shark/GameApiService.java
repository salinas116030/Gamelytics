package com.example.gamelytics.infrastructure.external.api.services.Shark;

import com.example.gamelytics.domain.Game;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GameApiService {
    @GET("games")
    Call<Game> getGame(@Query("id") int id);

    @GET("games")
    Call<List<Game>> searchGames(@Query("title") String title);
}

