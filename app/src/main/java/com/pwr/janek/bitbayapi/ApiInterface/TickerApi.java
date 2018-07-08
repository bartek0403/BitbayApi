package com.pwr.janek.bitbayapi.ApiInterface;

import com.pwr.janek.bitbayapi.Model.Ticker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface TickerApi {

    @GET
    Call<Ticker> getTicker(@Url String url);
}
