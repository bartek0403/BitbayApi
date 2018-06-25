package com.pwr.janek.bitbayapi.MVP;

import com.pwr.janek.bitbayapi.ApiInterface.BitbayOrderBookApi;
import com.pwr.janek.bitbayapi.Model.OrderBook;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements MVPContract.Presenter {

    @Nullable
    MVPContract.View view;
    MVPContract.Model model;

    BitbayOrderBookApi bitbayOrderBookApi;

    @Inject
    public Presenter(BitbayOrderBookApi bitbayOrderBookApi){
        this.bitbayOrderBookApi = bitbayOrderBookApi;

    }

    @Override
    public void setView(MVPContract.View view) {
        this.view = view;
    }

    @Override
    public void refresh() {

        if(view != null){
            Call<OrderBook> orderBookCall = bitbayOrderBookApi.getBitbayOrderBook();
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
