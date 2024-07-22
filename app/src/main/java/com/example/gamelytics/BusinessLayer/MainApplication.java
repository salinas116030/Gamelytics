package com.example.gamelytics.BusinessLayer;

import android.app.Application;

import com.blongho.country_data.Country;
import com.blongho.country_data.World;
import com.example.gamelytics.DataLayer.Basket;
import com.example.gamelytics.DataLayer.Game;
import com.example.gamelytics.DataLayer.GiftCard;

import java.util.ArrayList;

public class MainApplication extends Application {

    ResourceFetcher contentFetcher;
    GameDataFetcher gameFetcher;
    PriceDataFetcher priceFetcher;
    ApiFetcher apiFetcher;
    Game consultedGame;
    String currentCountryCode;
    Basket gameBasket;

    @Override
    public void onCreate() {
        World.init(getApplicationContext());
        super.onCreate();
    }

    // Getters and setters
    public Game getConsultedGame() {
        return consultedGame;
    }

    public void setConsultedGame(Game consultedGame) {
        this.consultedGame = consultedGame;
    }

    public Basket getGameBasket() {
        return gameBasket;
    }

    public void setGameBasket(Basket gameBasket) {
        this.gameBasket = gameBasket;
    }

    public String getCurrentCountryCode() {
        return currentCountryCode;
    }

    public void setCurrentCountryCode(String currentCountryCode) {
        this.currentCountryCode = currentCountryCode;
    }

    // Custom methods
    public void getGameAppID(String url, FetchResultListener listener) {
        contentFetcher = new ResourceFetcher(listener);
        contentFetcher.execute("https://steamcommunity.com/actions/SearchApps/" + url);
    }

    public void getGameDetails(String appid, FetchResultListener listener) {
        gameFetcher = new GameDataFetcher(Integer.parseInt(appid), listener);
        gameFetcher.execute("https://store.steampowered.com/api/appdetails?appids="
                                + appid + "&filters=basic");
    }
    public void getGamePrices(String appid, com.blongho.country_data.Country country,
                              FetchResultListener listener) {
        priceFetcher = new PriceDataFetcher(Integer.parseInt(appid), listener, this);
        priceFetcher.execute("https://store.steampowered.com/api/appdetails?appids="
                                + appid + "&cc=" + country.getAlpha2() + "&filters=price_overview");
    }

    public void getBasketForGame(String countryName, String currCode, int totalPrice,
                                 FetchResultListener listener) {
        countryName = countryName.replace(" ", "+");
        apiFetcher = new ApiFetcher(this, listener);
        apiFetcher.execute("http:/10.0.0.1:5000/combinatoria?country=" + countryName
                            + "&coin=" + currCode + "&amount=" + totalPrice);
    }
}
