package com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookMVP;

import com.pwr.janek.bitbayapi.ApiInterface.OrderBookApi;
import com.pwr.janek.bitbayapi.ApplicationDI.BitbayApp;
import com.pwr.janek.bitbayapi.Model.OrderBook;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements MVPOrderBOokContract.Presenter {

    @Nullable
    MVPOrderBOokContract.View view;

    @Inject
    OrderBookApi orderBookApi;

    private String cryptoTicker = "BTC";
    private String fiatTicker = "PLN";


    public Presenter(){
        BitbayApp.getAppComponent().inject(this);
    }

    @Override
    public void setView(MVPOrderBOokContract.View view) {
        this.view = view;
    }

    @Override
    public void refresh() {

        if(view != null){
            Call<OrderBook> orderBookCall = orderBookApi.getOrderBook(  cryptoTicker + fiatTicker + "/orderbook.json");
            orderBookCall.enqueue(new Callback<OrderBook>() {
                @Override
                public void onResponse(Call<OrderBook> call, Response<OrderBook> response) {
                    OrderBook orderBook = response.body();
                    view.displayOrderBook(orderBook);
                }

                @Override
                public void onFailure(Call<OrderBook> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void refreshCompleted(OrderBook orderBook) {
        view.displayOrderBook(orderBook);
    }
}
