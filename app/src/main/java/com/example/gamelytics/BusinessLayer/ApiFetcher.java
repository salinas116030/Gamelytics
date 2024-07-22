package com.example.gamelytics.BusinessLayer;

import android.os.AsyncTask;

import com.example.gamelytics.DataLayer.Basket;
import com.example.gamelytics.DataLayer.FlowStates;
import com.example.gamelytics.DataLayer.GiftCard;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.IOUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ApiFetcher extends AsyncTask<String, Void, String> {
    private FetchResultListener listener;
    private MainApplication application;

    protected ApiFetcher(MainApplication app, FetchResultListener listener) {
        this.application = app;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            return IOUtils.toString(new URL(strings[0]), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return (String) FlowStates.NOT_VALID_URL;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            if (result != null) {
                JSONObject jResult = new JSONObject(new JSONTokener(result));
                ArrayList<GiftCard> basket = new ArrayList<>();
                for (int i = 0; i < jResult.getInt("num_cards_used"); ++i) {
                    String price;
                    try {
                        price = jResult.getJSONArray("data").getJSONArray(i)
                                .getString(0);
                    } catch (NullPointerException | JSONException e) {
                        continue;
                    }
                    price = price.replace(",", ".");
                    double finalPrice = Double.valueOf(price.substring(0, price.indexOf("€")));
                    String shopname = jResult.getJSONArray("data").getJSONArray(i)
                            .getString(1);
                    String url = jResult.getJSONArray("data").getJSONArray(i)
                            .getString(2);
                    int amount = jResult.getJSONArray("used_cards").getInt(i);

                    GiftCard card = new GiftCard(finalPrice, amount, url, shopname,
                                                    application.getCurrentCountryCode());
                    basket.add(card);
                }
                application.setGameBasket(new Basket(basket));
                if (listener != null) listener.onResultFetched(application.getConsultedGame());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
