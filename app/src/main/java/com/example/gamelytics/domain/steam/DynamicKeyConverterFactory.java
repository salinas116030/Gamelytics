package com.example.gamelytics.domain.steam;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class DynamicKeyConverterFactory extends Converter.Factory {

    private final Gson gson;

    public DynamicKeyConverterFactory(Gson gson) {
        this.gson = gson;
    }

    public static DynamicKeyConverterFactory create() {
        return create(new Gson());
    }

    public static DynamicKeyConverterFactory create(Gson gson) {
        return new DynamicKeyConverterFactory(gson);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new Converter<ResponseBody, GameSteam>() {
            @Override
            public GameSteam convert(ResponseBody value) throws IOException {
                String jsonString = value.string();
                JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

                for (String key : jsonObject.keySet()) {
                    JsonElement gameInfoElement = jsonObject.get(key);
                    GameInfo gameInfo = gson.fromJson(gameInfoElement, GameInfo.class);
                    GameSteam gameSteam = new GameSteam();
                    gameSteam.setGameInfo(gameInfo);
                    return gameSteam;
                }
                return null;
            }
        };
    }
}
