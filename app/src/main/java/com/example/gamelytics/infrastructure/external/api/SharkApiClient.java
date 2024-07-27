package com.example.gamelytics.infrastructure.external.api;

import com.google.gson.Gson; //EJEMPLO DE MAL USO DE DEPENDENCIA. OJO AL ELEGIR EL TIPO DE DEPENDENCIAS.Uno es de google y otro de maven
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SharkApiClient {
    private static final String BASE_URL = "https://www.cheapshark.com/api/1.0/";
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

