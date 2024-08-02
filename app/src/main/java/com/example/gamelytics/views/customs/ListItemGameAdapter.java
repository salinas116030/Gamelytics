package com.example.gamelytics.views.customs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.gamelytics.R;
import com.example.gamelytics.domain.GameItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListItemGameAdapter extends ArrayAdapter<GameItem> {

    private final List<GameItem> games;
    private final LayoutInflater inflater;

    public ListItemGameAdapter(Context context, List<GameItem> games) {
        super(context, 0, games);
        this.games = games;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_game, parent, false);
        }

        GameItem game = games.get(position);

        TextView titleTextView = convertView.findViewById(R.id.game_title);
        ImageView logoImageView = convertView.findViewById(R.id.game_logo);

        titleTextView.setText(game.getTitle());
        Picasso.get().load(game.getLogo()).resize(100, 45).into(logoImageView);

        return convertView;
    }
}

