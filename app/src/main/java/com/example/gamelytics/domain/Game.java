package com.example.gamelytics.domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Game implements Serializable {

    @SerializedName("info")
    private GameInfo info;
    @SerializedName("cheapestPriceEver")
    private GameCheapestPriceEver cheapestPriceEver;
    @SerializedName("deals")
    private List<DealItem> deals;

    // Getters & Setters
    public GameInfo getInfo() {
        return info;
    }

    public void setInfo(GameInfo info) {
        this.info = info;
    }

    public GameCheapestPriceEver getCheapestPriceEver() {
        return cheapestPriceEver;
    }

    public void setCheapestPriceEver(GameCheapestPriceEver cheapestPriceEver) {
        this.cheapestPriceEver = cheapestPriceEver;
    }

    public List<DealItem> getDeals() {
        return deals;
    }

    public void setDeals(List<DealItem> deals) {
        this.deals = deals;
    }
}

