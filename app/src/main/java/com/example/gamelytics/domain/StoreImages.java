package com.example.gamelytics.domain;

import com.google.gson.annotations.SerializedName;

public class StoreImages {
    @SerializedName("logo")
    private String logo;
    @SerializedName("icon")
    private String icon;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setBanner(String icon) {
        this.icon = icon;
    }

}
