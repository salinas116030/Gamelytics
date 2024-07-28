package com.example.gamelytics.domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

// Inner classes for nested JSON objects
public class GameInfo implements Serializable {
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

