package com.example.gamelytics.DataLayer;

public class Country {

    // Attributes
    private String name;
    private String twoCode;
    private int flagImg;
    private String coinSymbol;
    private double price;

    // Constructor
    public Country(String name, String code, int flagCode, String symb, double price) {
        this.name = name;
        this.twoCode = code;
        this.flagImg = flagCode;
        this.coinSymbol = symb;
        this.price = price;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTwoCode() {
        return twoCode;
    }

    public void setTwoCode(String twoCode) {
        this.twoCode = twoCode;
    }

    public int getFlagImg() {
        return flagImg;
    }

    public void setFlagImg(int flagImg) {
        this.flagImg = flagImg;
    }

    public String getCoinSymbol() {
        return coinSymbol;
    }

    public void setCoinSymbol(String coinSymbol) {
        this.coinSymbol = coinSymbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
