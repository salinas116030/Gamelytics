package com.example.gamelytics.domain.shark;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class GameInfo implements Serializable {

    @SerializedName("steamAppID")
    private String steamAppId;
    @SerializedName("name")
    private String titleGame;
    @SerializedName("salePrice")
    private String salePrice;
    @SerializedName("retailPrice")
    private String retailPrice;
    @SerializedName("thumb")
    private String logoGame;
    @SerializedName("steamRatingText")
    private String rating;

    // Getters & Setters
    public String getSteamAppId() {
        return steamAppId;
    }

    public void setSteamAppId(String steamAppId) {
        this.steamAppId = steamAppId;
    }

    public String getTitleGame() {
        return titleGame;
    }

    public void setTitleGame(String titleGame) {
        this.titleGame = titleGame;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getLogoGame() {
        return logoGame;
    }

    public void setLogoGame(String logoGame) {
        this.logoGame = logoGame;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    // System methods
    @Override
    public String toString() {
        return "GameInfo{" +
                "steamAppId='" + steamAppId + '\'' +
                ", titleGame='" + titleGame + '\'' +
                ", salePrice='" + salePrice + '\'' +
                ", retailPrice='" + retailPrice + '\'' +
                ", logoGame='" + logoGame + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}