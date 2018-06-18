package com.pwr.janek.bitbayapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ContextModule.class)
public class BitbayOrderBookModule {

    @Provides
    public BitbayOrderBookApi getBitbayOrderBookApi(Retrofit retrofit){
        return retrofit.create(BitbayOrderBookApi.class);
    }


    @Provides
    public Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    public GsonConverterFactory getGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    public OkHttpClient getOkHttpClient(){
        return new OkHttpClient()
                .newBuilder()
                .build();
    }

    @BitbayOrderBookScope
    @Provides
    public Retrofit getRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://bitbay.net/API/Public")
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

}
