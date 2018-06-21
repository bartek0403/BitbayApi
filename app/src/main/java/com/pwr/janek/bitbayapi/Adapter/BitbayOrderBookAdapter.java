package com.pwr.janek.bitbayapi.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pwr.janek.bitbayapi.Model.OrderBook;
import com.pwr.janek.bitbayapi.R;

import java.util.List;

import retrofit2.Response;

/*
   Adapter do customowego widoku RECYCLERVIEW, layout pojedynczego pola w pliku "recyclerview_row"
 */

public class BitbayOrderBookAdapter extends RecyclerView.Adapter<BitbayOrderBookViewHolder> {

    private OrderBook orderBook;

    public BitbayOrderBookAdapter() {
    }

    @NonNull
    @Override
    public BitbayOrderBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row,
                parent, false);
        return new BitbayOrderBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BitbayOrderBookViewHolder holder, int position) {
        List<Double> orderBookElement = orderBook.getBids().get(position);
        holder.price.setText(String.valueOf(orderBookElement.get(0)));
        holder.depth.setText(String.valueOf(orderBookElement.get(1)));
    }

    @Override
    public int getItemCount() {
        return orderBook.getBids().size();
    }

    public void setItems(Response<OrderBook> orderBook) {
        this.orderBook = orderBook.body();
        notifyDataSetChanged();
    }


}
