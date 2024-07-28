package com.example.gamelytics.domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Game implements Serializable {

    @SerializedName("info")
    private Info info;
    @SerializedName("cheapestPriceEver")
    private CheapestPriceEver cheapestPriceEver;
    @SerializedName("deals")
    private List<Deal> deals;

    // Getters & Setters
    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public CheapestPriceEver getCheapestPriceEver() {
        return cheapestPriceEver;
    }

    public void setCheapestPriceEver(CheapestPriceEver cheapestPriceEver) {
        this.cheapestPriceEver = cheapestPriceEver;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    // Inner classes for nested JSON objects
    public static class Info implements Serializable {
        @SerializedName("title")
        private String title;
        @SerializedName("steamAppID")
        private String steamAppID;
        @SerializedName("thumb")
        private String thumb;

        // Getters & Setters
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSteamAppID() {
            return steamAppID;
        }

        public void setSteamAppID(String steamAppID) {
            this.steamAppID = steamAppID;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }
    }

    public static class CheapestPriceEver implements Serializable {
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

    public static class Deal implements Serializable {
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
}

