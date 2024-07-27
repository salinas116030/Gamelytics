package com.example.gamelytics.infrastructure.external.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SteamApiClient {
    private static final String BASE_URL = " http://store.steampowered.com/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
