package com.example.gamelytics.BusinessLayer;

import android.os.AsyncTask;
import android.util.Log;

import com.example.gamelytics.DataLayer.FlowStates;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.IOUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.net.URL;

public class PriceDataFetcher extends AsyncTask<String, Void, String> {
    private FetchResultListener listener;
    private int appid;
    private MainApplication application;

    protected PriceDataFetcher(int appid, FetchResultListener listener, MainApplication app) {
        this.appid = appid;
        this.listener = listener;
        this.application = app;
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
                jResult = jResult.getJSONObject(Integer.toString(this.appid))
                        .getJSONObject("data");
                jResult = jResult.getJSONObject("price_overview");
                String currency = jResult.getString("currency");
                double price = jResult.getInt("final");
                price = price / 100.0;
                application.getConsultedGame().setPriceForCurrency(currency, price);
                if (listener != null) listener.onResultFetched(application.getConsultedGame());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

