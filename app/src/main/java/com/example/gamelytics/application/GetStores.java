package com.example.gamelytics.application;

import com.example.gamelytics.domain.Store;
import com.example.gamelytics.domain.StoreRepository;
import java.util.List;

public class GetStores {
    private final StoreRepository storeRepository;

    public GetStores(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> execute() {
        return storeRepository.getAllStores();
    }
}

