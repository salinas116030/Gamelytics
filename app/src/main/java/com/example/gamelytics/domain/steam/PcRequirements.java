package com.example.gamelytics.domain.steam;

import java.io.Serializable;

public  class PcRequirements implements Serializable {
    private String minimum;

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }
}