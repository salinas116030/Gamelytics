package com.example.gamelytics.infrastructure;

import com.example.gamelytics.domain.Store;
import com.example.gamelytics.domain.StoreRepository;
import com.example.gamelytics.infrastructure.external.api.SharkApiClient;
import com.example.gamelytics.infrastructure.external.api.services.Shark.StoreApiService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.util.List;

public class ApiStoreRepository implements StoreRepository {
    private final StoreApiService storeApiService;

    public ApiStoreRepository() {
        Retrofit retrofit = SharkApiClient.getClient();
        storeApiService = retrofit.create(StoreApiService.class);
    }

    @Override
    public List<Store> getAllStores() {
        Call<List<Store>> call = storeApiService.getAllStores();
        try {
            Response<List<Store>> response = call.execute();
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

