package com.pwr.janek.bitbayapi.ApplicationFeatures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pwr.janek.bitbayapi.ApiInterface.BitbayOrderBookApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Provides
    @Singleton
    public BitbayOrderBookApi getBitbayOrderBookApi(Retrofit retrofit){
        return retrofit.create(BitbayOrderBookApi.class);
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
        return new OkHttpClient()
                .newBuilder()
                .build();
    }

    @Singleton
    @Provides
    public Retrofit getRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://bitbay.net/")
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

}
