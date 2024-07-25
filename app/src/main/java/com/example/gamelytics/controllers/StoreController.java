package com.example.gamelytics.controllers;

import com.example.gamelytics.application.GetStores;
import com.example.gamelytics.domain.Store;
import com.example.gamelytics.domain.StoreRepository;

import java.util.List;

public class StoreController {
    private final StoreRepository storeRepository;
    private final GetStores UseCaseGetStores;

    public StoreController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
        this.UseCaseGetStores = new GetStores(storeRepository);
    }

    public List<Store> getAllStores() {
        return UseCaseGetStores.execute();
    }
}

