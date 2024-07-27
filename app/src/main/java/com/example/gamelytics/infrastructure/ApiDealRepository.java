package com.example.gamelytics.infrastructure;

import com.example.gamelytics.domain.Deal;
import com.example.gamelytics.domain.DealRepository;
import com.example.gamelytics.infrastructure.external.api.SharkApiClient;
import com.example.gamelytics.infrastructure.external.api.services.Shark.DealApiService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiDealRepository implements DealRepository {
    private final DealApiService dealApiService;

    public ApiDealRepository() {
        Retrofit retrofit = SharkApiClient.getClient();
        dealApiService = retrofit.create(DealApiService.class);
    }

    @Override
    public Deal get(String id) {
        Call<Deal> call = dealApiService.getDeal(id);
        try {
            Response<Deal> response = call.execute();
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
