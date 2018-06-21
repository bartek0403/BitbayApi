package com.pwr.janek.bitbayapi.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pwr.janek.bitbayapi.R;


/*
    Klasa dla adaptera, zawiera przypisanie pojedynczych TextView w "recyclerview_row"
 */
public class BitbayOrderBookViewHolder extends RecyclerView.ViewHolder {

   public TextView price;
   public TextView depth;

    public BitbayOrderBookViewHolder(View itemView) {
        super(itemView);
        price = itemView.findViewById(R.id.price_row);
        depth = itemView.findViewById(R.id.depth_row);

    }
}
