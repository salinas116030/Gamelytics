package com.example.gamelytics.domain;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class GameItem implements Serializable {

    // Attributes
    @SerializedName("gameID")
    private int appID;
    @SerializedName("external")
    private String title;
    @SerializedName("thumb")
    private String logo;

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

    public int getAppID() {
        return appID;
    }

    public void setAppID(int appID) {
        this.appID = appID;
    }

    // System methods
    @NonNull
    @Override
    public String toString() {
        return this.title + "\n";
    }
}