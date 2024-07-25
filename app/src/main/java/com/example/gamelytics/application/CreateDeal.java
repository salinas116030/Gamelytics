package com.example.gamelytics.application;

import com.example.gamelytics.domain.Deal;
import com.example.gamelytics.domain.DealRepository;

public class CreateDeal {
    private final DealRepository dealRepository;

    public CreateDeal(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    public Deal execute(String id) {
        return dealRepository.get(id);
    }
}
