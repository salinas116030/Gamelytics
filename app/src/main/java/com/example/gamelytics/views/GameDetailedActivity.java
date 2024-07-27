package com.example.gamelytics.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blongho.country_data.World;
import com.example.gamelytics.BusinessLayer.FetchResultListener;
import com.example.gamelytics.BusinessLayer.MainApplication;
import com.example.gamelytics.DataLayer.AndroidCustoms.CustomPriceAdapter;
import com.example.gamelytics.DataLayer.Country;
import com.example.gamelytics.DataLayer.FlowStates;
import com.example.gamelytics.DataLayer.Game;
import com.example.gamelytics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GameDetailedActivity extends AppCompatActivity {

    Bundle bundle;
    TextView title, description, website;
    ImageView logo, pegi;
    int appid;
    ListView priceListView;
    MainApplication application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detailed);

        application = (MainApplication) getApplicationContext();
        title = (TextView) findViewById(R.id.titleTextView);
        description = (TextView) findViewById(R.id.descTextView);
        website = (TextView) findViewById(R.id.siteTextView);
        logo = (ImageView) findViewById(R.id.logoImageView);
        pegi = (ImageView) findViewById(R.id.pegiImageView);
        priceListView = (ListView) findViewById(R.id.priceListView);


        // Retrieve passed information from main screen
        bundle = getIntent().getExtras();
        appid = bundle.getInt("appid");

        ArrayList<Country> arrayList = new ArrayList<Country>();
        CustomPriceAdapter countryArrayAdapter = new CustomPriceAdapter(this, arrayList);
        priceListView.setAdapter(countryArrayAdapter);

        application.getGameDetails(Integer.toString(appid), new FetchResultListener() {
            @Override
            public void onResultFetched(Game result) {
                if (result != null) {
                    title.setText(result.getName());
                    description.setText(result.getShortDesc());
                    website.setText(result.getWebsite());
                    Picasso.get().load(result.getGameLogo()).into(logo);
                    String pegiAge = Integer.toString(result.getPegiAge());
                    String uri = "@drawable/icons8_pegi_" + pegiAge;
                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                    if (!pegiAge.equals("0")) {
                        pegi.setImageDrawable(getResources().getDrawable(imageResource));
                    }
                    application.setConsultedGame(result);
                } else {
                    // Implement suitable error handler
                }
            }
        });

        List<com.blongho.country_data.Country> countries = World.getAllCountries();
        //for (com.blongho.country_data.Country c: countries) {
        //    System.out.println("pais: " + c.toString());
        //}
        List<String> coinCodes = new ArrayList<>();

        for (com.blongho.country_data.Country country: countries) {
            if (coinCodes.contains(country.getCurrency().getCode())) {
                continue;
            } else {
                coinCodes.add(country.getCurrency().getCode());
                application.getGamePrices(Integer.toString(appid), country, new FetchResultListener() {
                    @Override
                    public void onResultFetched(Game result) {
                        String coin = country.getCurrency().getCode();
                        double price = result.getPriceForCurrency(coin);
                        if (price != FlowStates.CURRENCY_DONT_EXIST) {
                            Country c = new Country(country.getName(),
                                    country.getAlpha2(),
                                    World.getFlagOf(country.getAlpha2()),
                                    coin, price);
                            countryArrayAdapter.add(c);
                            countryArrayAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        }

        priceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country selCountry = countryArrayAdapter.getItem(position);
                application.setCurrentCountryCode(selCountry.getTwoCode());
                Bundle bundle = new Bundle();
                bundle.putString("countryName", selCountry.getName());
                bundle.putString("countryCode", selCountry.getTwoCode());
                bundle.putDouble("outPrice", selCountry.getPrice());
                bundle.putString("outCurr", selCountry.getCoinSymbol());
                Intent intent = new Intent(GameDetailedActivity.this, PriceDetailedActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, FlowStates.SHOW_DISPLAYACTIVITY);
            }
        });
    }

}