package com.example.gamelytics.controllers;

import com.example.gamelytics.application.CreateDeal;
import com.example.gamelytics.domain.Deal;
import com.example.gamelytics.domain.DealRepository;

public class DealController {
    private final DealRepository dealRepository;
    private final CreateDeal UseCaseCreateDeal;

    public DealController(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
        this.UseCaseCreateDeal = new CreateDeal(dealRepository);
    }

    public Deal getDeal(String id) {
        return UseCaseCreateDeal.execute(id);
    }
}

