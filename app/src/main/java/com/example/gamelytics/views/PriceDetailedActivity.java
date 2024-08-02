package com.example.gamelytics.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;

import android.os.Bundle;

import com.blongho.country_data.World;
import com.example.gamelytics.BusinessLayer.FetchResultListener;
import com.example.gamelytics.BusinessLayer.MainApplication;
import com.example.gamelytics.DataLayer.AndroidCustoms.CustomShopAdapter;
import com.example.gamelytics.DataLayer.Game;
import com.example.gamelytics.DataLayer.GiftCard;
import com.example.gamelytics.R;

import java.util.ArrayList;

public class PriceDetailedActivity extends AppCompatActivity {

    Bundle bundle;
    TextView localCurr, outCurr;
    ImageView countryFlag;
    ListView shopListView;
    MainApplication application;
    double outPrice;
    String currencyCode;
    String countryName;
    String countryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);

        application = (MainApplication) getApplicationContext();
        countryFlag = (ImageView) findViewById(R.id.dealImageView);
        localCurr = (TextView) findViewById(R.id.releaseDateTextView);
        outCurr = (TextView) findViewById(R.id.releaseDate);
        shopListView = (ListView) findViewById(R.id.shopListView);

        // Retrieve passed information from game screen
        bundle = getIntent().getExtras();
        outPrice = bundle.getDouble("outPrice");
        currencyCode = bundle.getString("outCurr");
        countryName = bundle.getString("countryName");
        countryCode = bundle.getString("countryCode");

        ArrayList<GiftCard> arrayList = new ArrayList<GiftCard>();
        CustomShopAdapter shopArrayAdapter = new CustomShopAdapter(this, arrayList);
        shopListView.setAdapter(shopArrayAdapter);

        countryFlag.setImageResource(World.getFlagOf(countryCode));

        // TODO Retrieve different shops
        /** JSON STRUCTURE RETURNAL
         * {
         *  data: [
         *      [
         *          precio,
         *          shopname,
         *          url
         *      ],
         *   ],
         *   minimum: -,
         *   num: -,
         *   used: [
         *      cardval
         *   ]
         * }
         */


        /**EXAMPLE WITH BUTTON ON HOW TO OPEN WEB BROWSER**/
        //Button button = findViewById(R.id.button);
        //button.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.com"));
                //startActivity(intent);
            //}
        //});
        /**END OF EXAMPLE**/

        application.getBasketForGame(countryName, currencyCode, (int) outPrice, new FetchResultListener() {
            @Override
            public void onResultFetched(Game result) {
                if (result != null) {
                    outCurr.setText(application.getGameBasket().getTotalPriceInCurrency() + " "
                                    + currencyCode);
                    localCurr.setText(application.getGameBasket().getTotalPriceInEUR() + "€");
                    for (GiftCard card: application.getGameBasket().getBasket()) {
                        shopArrayAdapter.add(card);
                        shopArrayAdapter.notifyDataSetChanged();
                    }
                }
            }
        });



        shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Open mobile web browser with URLs from the selected option
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(shopArrayAdapter.getItem(position).getUrl()));
                startActivity(intent);
            }
        });

    }
}