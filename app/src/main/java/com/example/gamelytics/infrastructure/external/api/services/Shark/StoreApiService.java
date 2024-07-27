package com.example.gamelytics.infrastructure.external.api.services.Shark;

import com.example.gamelytics.domain.Store;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StoreApiService {
    @GET("stores")
    Call<List<Store>> getAllStores();
}

