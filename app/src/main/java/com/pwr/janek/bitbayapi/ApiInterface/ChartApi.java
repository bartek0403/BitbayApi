package com.pwr.janek.bitbayapi.ApiInterface;

import com.pwr.janek.bitbayapi.Model.DailyChart;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ChartApi {
    @GET
    Call<DailyChart> getDailyChart(@Url String url);

}
