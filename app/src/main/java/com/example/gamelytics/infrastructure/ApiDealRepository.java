package com.example.gamelytics.infrastructure;

import com.example.gamelytics.domain.Deal;
import com.example.gamelytics.domain.DealRepository;
import com.example.gamelytics.infrastructure.external.api.SharkApiClient;
import com.example.gamelytics.infrastructure.external.api.services.Shark.DealApiService;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiDealRepository implements DealRepository {
    private final DealApiService dealApiService;
    private String decodedId;
    public ApiDealRepository() {
        Retrofit retrofit = SharkApiClient.getClient();
        dealApiService = retrofit.create(DealApiService.class);
    }

    @Override
    public Deal get(String id) {
        // Decodificar el id antes de pasarlo a Retrofit
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            decodedId = URLDecoder.decode(id, StandardCharsets.UTF_8);
        }

        Call<Deal> call = dealApiService.getDeal(decodedId);
        try {
            Response<Deal> response = call.execute();
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
