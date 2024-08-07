package com.example.gamelytics.views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gamelytics.R;
import com.example.gamelytics.domain.Deal;
import com.example.gamelytics.domain.DealRepository;
import com.example.gamelytics.domain.steam.GameSteam;
import com.example.gamelytics.domain.steam.Platforms;
import com.example.gamelytics.domain.steam.Screenshot;
import com.example.gamelytics.infrastructure.ApiDealRepository;
import com.example.gamelytics.infrastructure.ApiGameSteamRepository;
import com.example.gamelytics.infrastructure.internal.controllers.DealController;
import com.example.gamelytics.infrastructure.internal.controllers.steam.GameSteamController;
import com.example.gamelytics.views.customs.ListItemScreenshotAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DealActivity extends AppCompatActivity {

    private DealController dealController;
    private GameSteamController gameSteamController;
    private TextView releaseDate, retailPrice, salePrice, titleGame;
    private ImageView imageGame, imageWindows, imageMac, imageLinux;
    private ListView screenshotList;
    private Button buyGame;
    private String dealId,releaseRating;
    private Deal deal;
    private GameSteam gameSteam;
    private ImageView imageRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);

        DealRepository dealRepository = new ApiDealRepository();
        dealController = new DealController(dealRepository);
        ApiGameSteamRepository apiGameSteamRepository = new ApiGameSteamRepository();
        gameSteamController = new GameSteamController(apiGameSteamRepository);

        imageGame = findViewById(R.id.dealImageView);
        releaseDate = findViewById(R.id.releaseDateView);
        retailPrice = findViewById(R.id.retailPriceView);
        salePrice = findViewById(R.id.salePriceView);
        titleGame = findViewById(R.id.titleDealTextView);
        imageWindows = findViewById(R.id.imageWindowsView);
        imageMac = findViewById(R.id.imageMacView);
        imageLinux = findViewById(R.id.imageLinuxView);
        screenshotList = findViewById(R.id.screenshotListView);
        imageRating = findViewById(R.id.releaseRatingImageView);
        buyGame = findViewById(R.id.buyDealButton);

        Intent intent = getIntent();
        dealId = intent.getStringExtra("DEAL_ID");

        imageWindows.setVisibility(View.GONE);
        imageMac.setVisibility(View.GONE);
        imageLinux.setVisibility(View.GONE);
        imageRating.setVisibility(View.GONE);

        buyGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.cheapshark.com/redirect?dealID=" + dealId;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        new Thread(() -> {
            try {
                deal = dealController.getDeal(dealId);
                if(deal.getGameInfo().getSteamAppId() != null && deal.getGameInfo().getSteamAppId() != "" ){
                    gameSteam = gameSteamController.getGame(deal.getGameInfo().getSteamAppId());
                }

                runOnUiThread(() -> {
                    titleGame.setText(deal.getGameInfo().getTitleGame());
                    Picasso.get().load(deal.getGameInfo().getLogoGame()).into(imageGame);
                    retailPrice.setText(deal.getGameInfo().getRetailPrice());
                    salePrice.setText(deal.getGameInfo().getSalePrice());

                    releaseRating = deal.getGameInfo().getRating();
                    if (releaseRating != null) {
                        switch (releaseRating) {
                            case "Very Positive":
                                imageRating.setImageResource(R.drawable.four_stars);
                                imageRating.getLayoutParams().width = 250;
                                imageRating.getLayoutParams().height = 250;
                                break;
                            case "Overwhelmingly Positive":
                                imageRating.setImageResource(R.drawable.five_stars);
                                imageRating.getLayoutParams().width = 200;
                                imageRating.getLayoutParams().height = 200;
                                break;
                            default:
                                imageRating.setImageResource(R.drawable.three_stars);
                        }
                        imageRating.setVisibility(View.VISIBLE);
                    }

                    if(gameSteam !=null) {
                        releaseDate.setText(gameSteam.getGameInfo().getData().getReleaseDate().getDate());
                        List<Screenshot> screenshots = gameSteam.getGameInfo().getData().getScreenshots();
                        ListItemScreenshotAdapter screenshotAdapter = new ListItemScreenshotAdapter(this, screenshots);
                        screenshotList.setAdapter(screenshotAdapter);

                        Platforms platforms = gameSteam.getGameInfo().getData().getPlatforms();

                        imageWindows.setVisibility(platforms.isWindows() ? View.VISIBLE : View.GONE);
                        imageMac.setVisibility(platforms.isMac() ? View.VISIBLE : View.GONE);
                        imageLinux.setVisibility(platforms.isLinux() ? View.VISIBLE : View.GONE);

                        if (platforms.isWindows()) {
                            imageWindows.setImageResource(R.drawable.ic_windows);
                        }
                        if (platforms.isMac()) {
                            imageMac.setImageResource(R.drawable.ic_apple);
                        }
                        if (platforms.isLinux()) {
                            imageLinux.setImageResource(R.drawable.ic_linux);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(DealActivity.this, "Error searching deal values", Toast.LENGTH_SHORT).show());
            }
        }).start();


    }
}