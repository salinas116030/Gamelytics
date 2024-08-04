package com.example.gamelytics.views.customs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.example.gamelytics.R;
import com.example.gamelytics.domain.steam.Screenshot;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListItemScreenshotAdapter extends ArrayAdapter<Screenshot> {

    private final List<Screenshot> screenshots;
    private final LayoutInflater inflater;

    public ListItemScreenshotAdapter(Context context, List<Screenshot> screenshots) {
        super(context, 0, screenshots);
        this.screenshots = screenshots;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_screenshot, parent, false);
        }

        Screenshot screenshot = screenshots.get(position);

        ImageView thumbnailImageView = convertView.findViewById(R.id.screenshot_thumbnail);

        Picasso.get().load(screenshot.getPath_thumbnail()).into(thumbnailImageView);

        return convertView;
    }
}
