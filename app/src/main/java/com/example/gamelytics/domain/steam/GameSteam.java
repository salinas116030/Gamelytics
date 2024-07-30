package com.example.gamelytics.domain.steam;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GameSteam implements Serializable {
    private GameInfo gameInfo;

    public GameInfo getGameInfo(){
        return gameInfo;
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }
}

