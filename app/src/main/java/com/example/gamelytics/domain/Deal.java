package com.example.gamelytics.domain;


import androidx.annotation.NonNull;

public class Deal {

    // Attributes
    private String store;
    private String titleGame;
    private String logoGame;
    private String rating;
    private String salePrice;

    // Constructor
    public Deal(@NonNull String store, @NonNull String titleGame, @NonNull String logoGame, @NonNull String rating, @NonNull String salePrice) {
        this.store = store;
        this.titleGame = titleGame;
        this.logoGame = logoGame;
        this.rating = rating;
        this.salePrice = salePrice;
    }

    // Getters & Setters
    @NonNull
    public String getStore() {
        return store;
    }

    public void setStore(@NonNull String store) {
        this.store = store;
    }

    @NonNull
    public String getTitleGame() {
        return titleGame;
    }

    public void setTitleGame(@NonNull String titleGame) {
        this.titleGame = titleGame;
    }

    @NonNull
    public String getLogoGame() {
        return logoGame;
    }

    public void setLogoGame(@NonNull String logoGame) {
        this.logoGame = logoGame;
    }

    @NonNull
    public String getRating() {
        return rating;
    }

    public void setRating(@NonNull String rating) {
        this.rating = rating;
    }

    @NonNull
    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(@NonNull String salePrice) {
        this.salePrice = salePrice;
    }

    // System methods
    @NonNull
    @Override
    public String toString() {
        return "Deal{" +
                "store='" + store + '\'' +
                ", titleGame='" + titleGame + '\'' +
                ", logoGame='" + logoGame + '\'' +
                ", rating='" + rating + '\'' +
                ", salePrice='" + salePrice + '\'' +
                '}';
    }
}