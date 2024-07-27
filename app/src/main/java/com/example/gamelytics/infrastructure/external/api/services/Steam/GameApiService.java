package com.example.gamelytics.infrastructure.external.api.services.Steam;

import com.example.gamelytics.domain.Game;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GameApiService {
    @GET("appdetails")
    Call<Game> getGame(@Query("appids") int id);
}
