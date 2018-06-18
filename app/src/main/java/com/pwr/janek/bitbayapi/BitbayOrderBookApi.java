package com.pwr.janek.bitbayapi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BitbayOrderBookApi {
    @GET("/BTCPLN/orderbook.json")
    Call<OrderBook> getBitbayOrderBook();
}
