package com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityMVP;

import com.pwr.janek.bitbayapi.ApiInterface.TickerApi;
import com.pwr.janek.bitbayapi.ApplicationDI.BitbayApp;
import com.pwr.janek.bitbayapi.Model.Ticker;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements MVPMainActivityContract.Presenter{

    @Inject
    TickerApi tickerApi;

    private String cryptoTicker = "BTC";
    private String fiatTicker = "PLN";

    private MVPMainActivityContract.View view;

    public Presenter(){
        BitbayApp.getAppComponent().inject(this);}

    @Override
    public void setView(MVPMainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void fetchData() {
        if(view != null){
            Call<Ticker> tickerCall = tickerApi.getTicker(  cryptoTicker +fiatTicker+"/ticker.json");
            tickerCall.enqueue(new Callback<Ticker>() {
                @Override
                public void onResponse(Call<Ticker> call, Response<Ticker> response) {
                    Ticker ticker = response.body();
                    view.showData(ticker);
                }

                @Override
                public void onFailure(Call<Ticker> call, Throwable t) {
                   t.printStackTrace();
                    view.showFailureMsg();
                }
            });
        }
    }

    @Override
    public void saveCryptoTicker(String crypoTicker) {
        this.cryptoTicker = crypoTicker;
    }

    @Override
    public void saveFiatTicker(String fiatTicker) {
        this.fiatTicker = fiatTicker;
    }
}
