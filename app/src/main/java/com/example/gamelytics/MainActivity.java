package com.example.gamelytics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gamelytics.domain.GameItem;
import com.example.gamelytics.domain.GameRepository;
import com.example.gamelytics.infrastructure.ApiGameRepository;
import com.example.gamelytics.infrastructure.internal.controllers.GameController;
import com.example.gamelytics.views.GameDetailedActivity;
import com.example.gamelytics.views.customs.ListItemGameAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GameController gameController;
    private SearchView searchView;
    private ListView listView;
    private ArrayAdapter<GameItem> adapter;
    private List<GameItem> gameList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa el controlador de juegos
        GameRepository gameRepository = new ApiGameRepository();
        gameController = new GameController(gameRepository);
        gameList = new ArrayList<>();

        searchView = findViewById(R.id.search_view);
        listView = findViewById(R.id.list_view);

        adapter = new ListItemGameAdapter(MainActivity.this, gameList);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchGames(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameItem selectedGame = adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, GameDetailedActivity.class);
                intent.putExtra("GAME_ID", selectedGame.getAppID());
                startActivity(intent);
            }
        });
    }

    private void searchGames(String query) {
        new Thread(() -> {
            try {
                List<GameItem> games = gameController.searchAllGames(query);
                runOnUiThread(() -> {
                    if (games != null && !games.isEmpty()) {
                        gameList = games;
                        adapter.clear();
                        for (GameItem game : games) {
                            adapter.add(game);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "No games found", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error searching games", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}
