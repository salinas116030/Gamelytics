package com.example.gamelytics.domain;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GameItem implements Serializable {

    // Attributes
    @SerializedName("gameID")
    private int appID;
    @SerializedName("external")
    private String title;
    @SerializedName("thumb")
    private String logo;
    @SerializedName("date")
    private String cheapestPriceHistory;
    private Map<String, Store> stores;


    // Constructor
    public GameItem() {
        this.stores = new HashMap<String, Store>();
    }

    // Getters & Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCheapestPriceHistory() {
        return cheapestPriceHistory;
    }

    public void setCheapestPriceHistory(String cheapestPriceHistory) {
        this.cheapestPriceHistory = cheapestPriceHistory;
    }

    public Map<String, Store> getStores() {
        return stores;
    }

    public void setStores(Map<String, Store> stores) {
        this.stores = stores;
    }

    public int getAppID() {
        return appID;
    }

    public void setAppID(int appID) {
        this.appID = appID;
    }

    // Custom methods
    public Store getStoreByDealId(String dealId) {
        return stores.get(dealId);
    }

    public void addStore(String dealId, Store store) {
        stores.put(dealId, store);
    }

    // System methods
    @NonNull
    @Override
    public String toString() {
        return this.title + "\n";
    }
}