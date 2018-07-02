package com.pwr.janek.bitbayapi.OrderBookFeatures.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pwr.janek.bitbayapi.R;


/*
    Klasa dla adaptera, zawiera przypisanie pojedynczych TextView w "recyclerview_row"
 */
public class OrderBookViewHolder extends RecyclerView.ViewHolder {

   public TextView price;
   public TextView depth;

    public OrderBookViewHolder(View itemView) {
        super(itemView);
        price = itemView.findViewById(R.id.price_row);
        depth = itemView.findViewById(R.id.depth_row);

    }
}
