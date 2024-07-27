package com.example.gamelytics.infrastructure.external.api.services.Shark;

import com.example.gamelytics.domain.Deal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DealApiService {
    @GET("deals")
    Call<Deal> getDeal(@Query("id") String id);
}

