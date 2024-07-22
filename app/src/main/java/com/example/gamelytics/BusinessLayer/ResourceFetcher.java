package com.example.gamelytics.BusinessLayer;

import org.json.*;

import java.io.IOException;
import java.net.URL;

import android.graphics.Picture;
import android.os.AsyncTask;
import android.util.Log;

import com.example.gamelytics.DataLayer.Game;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.IOUtils;

import static com.example.gamelytics.DataLayer.FlowStates.NOT_VALID_URL;

public class ResourceFetcher extends AsyncTask<String, Void, String> {

    private FetchResultListener listener;

    protected ResourceFetcher(FetchResultListener listener) {
        this.listener = listener;
    }


    @Override
    protected String doInBackground(String... strings) {
        try {
            return IOUtils.toString(new URL(strings[0]), "UTF-8");
        } catch (IOException  e) {
            e.printStackTrace();
            return (String) NOT_VALID_URL;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            if (result != null) {
                JSONArray jResult = new JSONArray(new JSONTokener(result));
                for (int i = 0; i < jResult.length(); ++i) {
                    Game game = new Game();
                    String name = jResult.getJSONObject(i).getString("name");
                    int id = Integer.parseInt(jResult.getJSONObject(i).getString("appid"));
                    game.setName(name);
                    game.setAppID(id);
                    if (listener != null) listener.onResultFetched(game);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
