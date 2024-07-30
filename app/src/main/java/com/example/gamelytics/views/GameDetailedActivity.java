package com.example.gamelytics.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gamelytics.R;
import com.example.gamelytics.domain.Game;
import com.example.gamelytics.domain.GameRepository;
import com.example.gamelytics.domain.steam.GameSteam;
import com.example.gamelytics.domain.steam.GameSteamRepository;
import com.example.gamelytics.infrastructure.ApiGameRepository;
import com.example.gamelytics.infrastructure.ApiGameSteamRepository;
import com.example.gamelytics.infrastructure.internal.controllers.GameController;
import com.example.gamelytics.infrastructure.internal.controllers.steam.GameSteamController;
import com.example.gamelytics.views.customs.ListItemDealAdapter;
import com.squareup.picasso.Picasso;

public class GameDetailedActivity extends AppCompatActivity {

    private GameController gameController;
    private GameSteamController gameSteamController;
    private TextView title, description, website;
    private ImageView logo, pegi;
    private int gameid;
    ListView storeListView;
    private Game game;
    private GameSteam gameSteam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detailed);

        GameRepository gameRepository = new ApiGameRepository();
        gameController = new GameController(gameRepository);
        GameSteamRepository gameSteamRepository = new ApiGameSteamRepository();
        gameSteamController = new GameSteamController(gameSteamRepository);

        title = (TextView) findViewById(R.id.titleTextView);
        description = (TextView) findViewById(R.id.descTextView);
        website = (TextView) findViewById(R.id.siteTextView);
        logo = (ImageView) findViewById(R.id.logoImageView);
        pegi = (ImageView) findViewById(R.id.pegiImageView);
        storeListView = (ListView) findViewById(R.id.storeListView);

        Intent intent = getIntent();
        gameid = intent.getIntExtra("GAME_ID",-1);

        new Thread(() -> {
            try {
                game = gameController.getGame(gameid);
                if(game.getInfo().getSteamAppID() != null && game.getInfo().getSteamAppID() != "" ){
                    gameSteam = gameSteamController.getGame(game.getInfo().getSteamAppID());
                }

                runOnUiThread(() -> {
                    title.setText(game.getInfo().getTitle());
                    Picasso.get().load(game.getInfo().getThumb()).into(logo);

                    if(gameSteam !=null) {
                        description.setText(gameSteam.getGameInfo().getData().getDetailedDescription());
                        website.setText(gameSteam.getGameInfo().getData().getWebsite());
                        String pegiAge = Integer.toString(gameSteam.getGameInfo().getData().getRequiredAge());
                        String uri = "@drawable/icons8_pegi_" + pegiAge;
                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                        pegi.setImageDrawable(getResources().getDrawable(imageResource));
                    }

                    ListItemDealAdapter dealAdapter = new ListItemDealAdapter(this, game.getDeals());
                    storeListView.setAdapter(dealAdapter);
                });
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(GameDetailedActivity.this, "Error searching games", Toast.LENGTH_SHORT).show());
            }
        }).start();

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