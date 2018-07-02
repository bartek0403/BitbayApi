package com.pwr.janek.bitbayapi.OrderBookFeatures.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pwr.janek.bitbayapi.Model.OrderBook;
import com.pwr.janek.bitbayapi.R;

import java.util.List;

/*
   Adapter do customowego widoku RECYCLERVIEW, layout pojedynczego pola w pliku "recyclerview_row"
 */

public class OrderBookAdapter extends RecyclerView.Adapter<OrderBookViewHolder> {

    private OrderBook orderBook;
    private String type;

    public OrderBookAdapter(String type) {
        this.type = type;
    }

    @NonNull
    @Override
    public OrderBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row,
                parent, false);
        return new OrderBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderBookViewHolder holder, int position) {
        List<Double> orderBookElement;
        if(type.equals("ask")){
            orderBookElement = orderBook.getAsks().get(position);
        }
        else if(type.equals("bid")){
            orderBookElement = orderBook.getBids().get(position);
        }
        else {
            Log.i("OrderBookAdapter", "onBindViewHolder: incorrect adapter type, switching to default /ask/ ");
            orderBookElement = orderBook.getBids().get(position);
        }

        holder.price.setText(String.valueOf(orderBookElement.get(0)));
        holder.depth.setText(String.valueOf(orderBookElement.get(1)));
    }

    @Override
    public int getItemCount() {
        return orderBook.getBids().size();
    }

    public void setItems(OrderBook orderBook) {
        this.orderBook = orderBook;
        notifyDataSetChanged();
    }


}
