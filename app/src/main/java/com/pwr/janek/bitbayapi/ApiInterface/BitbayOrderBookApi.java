package com.pwr.janek.bitbayapi.ApiInterface;

import com.pwr.janek.bitbayapi.Model.OrderBook;

import retrofit2.Call;
import retrofit2.http.GET;

/*
    Interfejs dla Retrofit
 */

public interface BitbayOrderBookApi {
    @GET("/API/Public/BTCPLN/orderbook.json")
    Call<OrderBook> getBitbayOrderBook();
}
