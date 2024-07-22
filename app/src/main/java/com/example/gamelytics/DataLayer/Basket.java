package com.example.gamelytics.DataLayer;

import java.util.ArrayList;

public class Basket {

    private ArrayList<GiftCard> basket;
    private double totalPriceInEUR;
    private double totalPriceInCurrency;

    public Basket(ArrayList<GiftCard> basket) {
        this.basket = basket;
        this.totalPriceInEUR = 0.00;
        this.totalPriceInCurrency = 0.00;
        for (GiftCard card: basket) {
            this.totalPriceInCurrency += (double) card.getAmount();
            this.totalPriceInEUR += card.getPriceInEur();
        }
    }

    public ArrayList<GiftCard> getBasket() {
        return basket;
    }

    public void setBasket(ArrayList<GiftCard> basket) {
        this.basket = basket;
    }

    public double getTotalPriceInEUR() {
        return totalPriceInEUR;
    }

    public void setTotalPriceInEUR(double totalPriceInEUR) {
        this.totalPriceInEUR = totalPriceInEUR;
    }

    public double getTotalPriceInCurrency() {
        return totalPriceInCurrency;
    }

    public void setTotalPriceInCurrency(double totalPriceInCurrency) {
        this.totalPriceInCurrency = totalPriceInCurrency;
    }
}
