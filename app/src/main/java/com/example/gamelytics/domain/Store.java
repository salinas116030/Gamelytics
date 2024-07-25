package com.example.gamelytics.domain;

import androidx.annotation.NonNull;

public class Store {

    // Attributes
    private String name;
    private String logo;
    private String banner;

    // Constructor
    public Store() {
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    // System methods
    @NonNull
    @Override
    public String toString() {
        return "Store{name='" + name + "', logo='" + logo + "', banner='" + banner + "'}";
    }
}