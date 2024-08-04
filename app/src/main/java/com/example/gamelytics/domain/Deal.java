package com.example.gamelytics.domain;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import com.example.gamelytics.domain.shark.GameInfo;

public class Deal implements Serializable {
    @SerializedName("gameInfo")
    private GameInfo gameInfo;

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }
}
