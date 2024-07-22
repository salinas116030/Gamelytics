package com.example.gamelytics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.AdapterView;

import com.example.gamelytics.BusinessLayer.FetchResultListener;
import com.example.gamelytics.BusinessLayer.MainApplication;
import com.example.gamelytics.DataLayer.FlowStates;
import com.example.gamelytics.DataLayer.Game;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    // App components
    SearchView searchView;
    ListView listView;
    static ArrayAdapter<Game> adapter;
    MainApplication applicationController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = (SearchView) findViewById(R.id.searchbar);
        listView = (ListView) findViewById(R.id.listView);
        applicationController = (MainApplication) getApplicationContext();
        adapter = new ArrayAdapter<>(this, R.layout.row_layout, R.id.listText);

        listView.setAdapter(adapter);

        // Query submitted
        // TODO Still show up repeated games??
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.clear();
                applicationController.getGameAppID(query, new FetchResultListener() {
                    @Override
                    public void onResultFetched(Game result) {
                        if (result != null) adapter.add(result);
                        else; // Suitable error handler
                        Set<Game> reorderAdapter = new HashSet<>();
                        for (int i = 0; i < adapter.getCount(); ++i) reorderAdapter.add(adapter.getItem(i));
                        adapter.clear();
                        adapter.addAll(reorderAdapter);
                        adapter.notifyDataSetChanged();
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
/*                // Real time show-up
                adapter.clear();
                applicationController.getGameAppID(query, new FetchResultListener() {
                    @Override
                    public void onResultFetched(Game result) {
                        if (result != null) adapter.add(result);
                        else; // Suitable error handler
                        Set<Game> reorderAdapter = new HashSet<>();
                        for (int i = 0; i < adapter.getCount(); ++i) reorderAdapter.add(adapter.getItem(i));
                        adapter.clear();
                        adapter.addAll(reorderAdapter);
                        adapter.notifyDataSetChanged();
                    }
                });*/
                return false;
            }
        });

        // List item selected
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Game selGame = adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putInt("appid", selGame.getAppID());
                Intent intent = new Intent(MainActivity.this, GameDetailedActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, FlowStates.SHOW_DISPLAYACTIVITY);
            }
        });
    }
}