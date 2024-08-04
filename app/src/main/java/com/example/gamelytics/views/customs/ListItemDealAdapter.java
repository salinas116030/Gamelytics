package com.example.gamelytics.views.customs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gamelytics.R;
import com.example.gamelytics.domain.DealItem;
import com.example.gamelytics.domain.Store;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListItemDealAdapter extends BaseAdapter {
    private Context context;
    private List<DealItem> deals;
    private List<Store> stores;
    private String icon;

    public ListItemDealAdapter(Context context, List<DealItem> deals, List<Store> stores) {
        this.context = context;
        this.deals = deals;
        this.stores = stores;
    }

    public void updateData(List<DealItem> newDeals, List<Store> newStores) {
        this.deals = newDeals;
        this.stores = newStores;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return deals.size();
    }

    @Override
    public Object getItem(int position) {
        return deals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_deal, parent, false);
        }

        DealItem deal = deals.get(position);

        ImageView logoImageView = convertView.findViewById(R.id.logoImageView);
        TextView storeIDTextView = convertView.findViewById(R.id.storeIDTextView);
        TextView priceTextView = convertView.findViewById(R.id.priceTextView);
        TextView retailPriceTextView = convertView.findViewById(R.id.retailPriceTextView);
        TextView savingsTextView = convertView.findViewById(R.id.savingsTextView);

        for (Store store :stores){
            if(store.getId().equals(deal.getStoreID())){
                icon = store.getStoreImages().getIcon();
                break;
            }
        };

        Picasso.get().load("https://www.cheapshark.com"+icon).resize(16, 16).into(logoImageView);
        storeIDTextView.setText(deal.getStoreID());
        priceTextView.setText(deal.getPrice());
        retailPriceTextView.setText(deal.getRetailPrice());
        savingsTextView.setText(deal.getSavings()+ "%");

        return convertView;
    }
}
