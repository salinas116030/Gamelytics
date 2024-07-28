package com.example.gamelytics.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gamelytics.R;
import com.example.gamelytics.domain.GameItem;
import com.example.gamelytics.domain.GameRepository;
import com.example.gamelytics.infrastructure.ApiGameRepository;
import com.example.gamelytics.infrastructure.internal.controllers.GameController;

public class GameDetailedActivity extends AppCompatActivity {

    private GameController gameController;
    private TextView title, description, website;
    private ImageView logo, pegi;
    private int gameid;
    ListView storeListView;
    private GameItem game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detailed);

        GameRepository gameRepository = new ApiGameRepository();
        gameController = new GameController(gameRepository);

        title = (TextView) findViewById(R.id.titleTextView);
        description = (TextView) findViewById(R.id.descTextView);
        website = (TextView) findViewById(R.id.siteTextView);
        logo = (ImageView) findViewById(R.id.logoImageView);
        pegi = (ImageView) findViewById(R.id.pegiImageView);
        storeListView = (ListView) findViewById(R.id.storeListView);

        // Retrieve passed information from main screen

        Intent intent = getIntent();
        gameid = intent.getIntExtra("GAME_ID",-1);
        title.setText(String.valueOf(gameid));

        /*
        ArrayList<Country> arrayList = new ArrayList<Country>();
        CustomPriceAdapter countryArrayAdapter = new CustomPriceAdapter(this, arrayList);
        storeListView.setAdapter(countryArrayAdapter);
        */

        new Thread(() -> {
            try {
                game = gameController.getGame(gameid);

                description.setText(game.getTitle());
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(GameDetailedActivity.this, "Error searching games", Toast.LENGTH_SHORT).show());
            }
        }).start();



        /*
        application.getGameDetails(Integer.toString(gameid), new FetchResultListener() {
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

         */

        //Game game = gameController.getGame(gameid);

        // Aqui falta poner el controller que llame endpoint getAllStores y obtener storeListView custom con logoStore,nombre,precio

        // Go next view
        /*
        storeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country selCountry = countryArrayAdapter.getItem(position);
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

         */
    }

}