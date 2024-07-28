package com.example.gamelytics.domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class GameCheapestPriceEver implements Serializable {
    @SerializedName("price")
    private String price;
    @SerializedName("date")
    private long date;

    // Getters & Setters
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}