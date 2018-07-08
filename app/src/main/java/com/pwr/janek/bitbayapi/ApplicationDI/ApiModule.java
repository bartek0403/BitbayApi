package com.pwr.janek.bitbayapi.ApplicationDI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pwr.janek.bitbayapi.ApiInterface.OrderBookApi;
import com.pwr.janek.bitbayapi.ApiInterface.TickerApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public OrderBookApi getOrderBookApi(Retrofit retrofit){
        return retrofit.create(OrderBookApi.class);
    }

    @Provides
    @Singleton
    public TickerApi getTickerApi(Retrofit retrofit){
        return retrofit.create(TickerApi.class);
    }

    @Provides
    @Singleton
    public Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public GsonConverterFactory getGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    public OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor interceptor = getHttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor getHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor();
    }

    @Singleton
    @Provides
    public Retrofit getRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://bitbay.net/API/Public/")
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

}
