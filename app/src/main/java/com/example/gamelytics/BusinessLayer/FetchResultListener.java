package com.example.gamelytics.BusinessLayer;

import com.example.gamelytics.DataLayer.Game;

import org.json.JSONArray;

public interface FetchResultListener {
    void onResultFetched(Game result);
}