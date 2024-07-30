package com.example.gamelytics.infrastructure.external.api.services.Steam;

import com.example.gamelytics.domain.steam.GameSteam;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GameApiService {
    @GET("appdetails")
    Call<GameSteam> getGame(@Query("appids") String id);
}
