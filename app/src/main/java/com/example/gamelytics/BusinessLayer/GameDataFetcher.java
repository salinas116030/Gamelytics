package com.example.gamelytics.BusinessLayer;

import android.os.AsyncTask;
import android.util.Log;

import com.example.gamelytics.DataLayer.FlowStates;
import com.example.gamelytics.DataLayer.Game;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.IOUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.net.URL;

public class GameDataFetcher extends AsyncTask<String, Void, String> {
    private FetchResultListener listener;
    private int appid;

    protected GameDataFetcher(int appid, FetchResultListener listener) {
        this.appid = appid;
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
            Game game = new Game();

            if (result != null) {
                JSONObject jResult = new JSONObject(new JSONTokener(result));
                jResult = jResult.getJSONObject(Integer.toString(this.appid))
                        .getJSONObject("data");
                try {
                    JSONObject price_overview = jResult.getJSONObject("price_overview");
                    String final_formatted = price_overview.getString("final_formatted");
                    if (final_formatted.contains(",")) {
                        final_formatted = final_formatted.replaceAll(",",".");
                    }
                    String[] strArr = final_formatted.split(" ");
                    game.setPriceForCurrency(strArr[0], Double.parseDouble(strArr[1]));
                } catch (JSONException e) {
                    // As price_overview filter is not declared create suitable handler
                }
                String name = jResult.getString("name");
                String desc = jResult.getString("short_description");
                String web = jResult.getString("website");
                int pegi = (Integer) Integer.parseInt(jResult.getString("required_age"));
                String imgUrl = jResult.getString("header_image");

                game.setName(name);
                game.setShortDesc(desc);
                game.setWebsite(web);
                game.setGameLogo(imgUrl);
                game.setPegiAge(pegi);
                if (listener != null) listener.onResultFetched(game);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
