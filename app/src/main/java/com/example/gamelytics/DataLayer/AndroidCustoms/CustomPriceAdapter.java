package com.example.gamelytics.DataLayer.AndroidCustoms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gamelytics.DataLayer.Country;
import com.example.gamelytics.R;

import java.util.ArrayList;

public class CustomPriceAdapter extends ArrayAdapter<Country> {

    public CustomPriceAdapter(@NonNull Context context, ArrayList<Country> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext())
                                .inflate(R.layout.row_country_layout, parent, false);
        }
        Country country = getItem(position);

        // Search for every element in the row view
        ImageView flag = (ImageView) currentItemView.findViewById(R.id.flagImageView);
        TextView name = (TextView) currentItemView.findViewById(R.id.nameTextView);
        TextView price = (TextView) currentItemView.findViewById(R.id.priceTextView);

        // Set view resources
        flag.setImageResource(country.getFlagImg());
        name.setText(country.getName());
        price.setText(Double.toString(country.getPrice()) + " " + country.getCoinSymbol());
        return currentItemView;
    }
}
