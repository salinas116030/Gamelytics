package com.example.gamelytics.domain;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Store {

    // Attributes
    @SerializedName("storeID")
    private String id;
    @SerializedName("storeName")
    private String name;
    @SerializedName("images")
    private StoreImages storeImages;

    // Constructor
    public Store() {
    }

    // Getters & Setters
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StoreImages getStoreImages() {
        return storeImages;
    }

    public void setStoreImages(StoreImages storeImages) {
        this.storeImages = storeImages;
    }

    // System methods
    @NonNull
    @Override
    public String toString() {
        return "Store{name='" + name + "'}";
    }
}