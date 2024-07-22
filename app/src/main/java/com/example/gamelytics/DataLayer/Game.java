package com.example.gamelytics.DataLayer;

import static com.example.gamelytics.DataLayer.FlowStates.*;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class Game {

    // Attributes
    private String name;
    private String shortDesc;
    private String gameLogo;
    private int pegiAge;
    private String website;
    private HashMap<String, Double> priceList;
    private int appID;


    // Constructor
    public Game() {
        this.priceList = new HashMap<String, Double>();
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getGameLogo() {
        return gameLogo;
    }

    public void setGameLogo(String gameLogo) {
        this.gameLogo = gameLogo;
    }

    public int getPegiAge() {
        return pegiAge;
    }

    public void setPegiAge(int pegiAge) {
        this.pegiAge = pegiAge;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public HashMap<String, Double> getPriceList() {
        return priceList;
    }

    public void setPriceList(HashMap<String, Double> priceList) {
        this.priceList = priceList;
    }

    public int getAppID() {
        return appID;
    }

    public void setAppID(int appID) {
        this.appID = appID;
    }

    // Custom methods
    public double getPriceForCurrency(String coin) {
        double price;
        try {
            price = priceList.get(coin);
        } catch (NullPointerException e) {
            //System.out.println("Not registered currency for game: " + this.name + "\n");
            price = CURRENCY_DONT_EXIST;
        }
        return price;
    }

    public void setPriceForCurrency(String coin, double price) {
        priceList.put(coin, price);
    }

    // System methods
    @NonNull
    @Override
    public String toString() {
        return this.name + "\n";
    }
}
