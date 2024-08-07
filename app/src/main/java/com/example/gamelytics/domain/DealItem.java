package com.example.gamelytics.domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class DealItem implements Serializable {
    @SerializedName("storeID")
    private String storeID;
    @SerializedName("dealID")
    private String dealID;
    @SerializedName("price")
    private String price;
    @SerializedName("retailPrice")
    private String retailPrice;
    @SerializedName("savings")
    private String savings;

    // Getters & Setters
    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getDealID() {
        return dealID;
    }

    public void setDealID(String dealID) {
        this.dealID = dealID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getSavings() {
        return savings;
    }

    public void setSavings(String savings) {
        this.savings = savings;
    }
}
