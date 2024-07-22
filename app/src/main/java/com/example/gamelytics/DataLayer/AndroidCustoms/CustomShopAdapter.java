package com.example.gamelytics.DataLayer.AndroidCustoms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gamelytics.DataLayer.GiftCard;
import com.example.gamelytics.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomShopAdapter extends ArrayAdapter<GiftCard> {

    public CustomShopAdapter(@NonNull Context context, ArrayList<GiftCard> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.row_shop_layout, parent, false);
        }

        GiftCard card = getItem(position);

        // Search for every element in the row view
        TextView cardsInfo = (TextView) currentItemView.findViewById(R.id.cardTextView);
        TextView totalPrice = (TextView) currentItemView.findViewById(R.id.totalPriceTextView);
        TextView shopLogo = (TextView) currentItemView.findViewById(R.id.buttonShop);

        // Set the view resources
        cardsInfo.setText(card.toString());
        totalPrice.setText(card.getPriceInEur() + "€");
        shopLogo.setText(card.getShopName());

        return currentItemView;
    }
}
