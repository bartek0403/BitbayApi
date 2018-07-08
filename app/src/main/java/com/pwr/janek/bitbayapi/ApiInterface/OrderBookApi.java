package com.pwr.janek.bitbayapi.ApiInterface;

import com.pwr.janek.bitbayapi.Model.OrderBook;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/*
    Interfejs dla Retrofit
 */
public  interface OrderBookApi {
    @GET
    Call<OrderBook> getOrderBook(@Url String url);
}

