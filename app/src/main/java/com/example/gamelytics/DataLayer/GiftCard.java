package com.example.gamelytics.DataLayer;

import androidx.annotation.NonNull;

public class GiftCard {

    // Attribute
    private double priceInEur;
    private int amount;
    private String url;
    private String shopName;
    private String coinCode;

    // Constructor
    public GiftCard(double price, int amount, String url, String shop, String coinCode) {
        this.priceInEur = price;
        this.amount = amount;
        this.url = url;
        this.shopName = shop;
        this.coinCode = coinCode;
    }

    // Getters and setters
    public double getPriceInEur() {
        return priceInEur;
    }

    public void setPriceInEur(int amount) {
        this.priceInEur = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setPriceInEur(double priceInEur) {
        this.priceInEur = priceInEur;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    @NonNull
    @Override
    public String toString() {
        return "Steam wallet gift-card " + this.amount + " " + this.coinCode;
    }
}
