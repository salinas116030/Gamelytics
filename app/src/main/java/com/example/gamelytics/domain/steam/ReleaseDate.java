package com.example.gamelytics.domain.steam;

import java.io.Serializable;

public class ReleaseDate implements Serializable {
    private boolean comingSoon;
    private String date;

    public boolean isComingSoon() {
        return comingSoon;
    }

    public void setComingSoon(boolean comingSoon) {
        this.comingSoon = comingSoon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
