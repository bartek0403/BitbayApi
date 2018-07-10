package com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityMVP;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.pwr.janek.bitbayapi.ApiInterface.ChartApi;
import com.pwr.janek.bitbayapi.ApiInterface.TickerApi;
import com.pwr.janek.bitbayapi.ApplicationDI.BitbayApp;
import com.pwr.janek.bitbayapi.Model.DailyChart;
import com.pwr.janek.bitbayapi.Model.Datum;
import com.pwr.janek.bitbayapi.Model.Ticker;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements MVPMainActivityContract.Presenter{

    @Inject
    TickerApi tickerApi;

    @Inject
    ChartApi chartApi;

    @Inject
    Context context;

    private DailyChart chart;

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
                    view.showFailureMsg("Failed fetching ticker");
                }
            });
            Call<DailyChart> dailyChartCall = chartApi.getDailyChart("https://min-api.cryptocompare.com/data/histoday?fsym=" +
                    cryptoTicker +"&tsym=" +
                    fiatTicker + "&limit=30&e=Bitbay");
            dailyChartCall.enqueue(new Callback<DailyChart>() {
                @Override
                public void onResponse(Call<DailyChart> call, Response<DailyChart> response) {
                    chart = response.body();
                    prepareChartData();
                }

                @Override
                public void onFailure(Call<DailyChart> call, Throwable t) {
                    t.printStackTrace();
                    view.showFailureMsg("Failed fetching chart");
                }
            });
        }
    }

    private void prepareChartData() {

        if (chart != null) {
            List<Datum> chartData = chart.getData();
            List<Entry> entries = new ArrayList<Entry>();

            //TODO Zamienić timestamp na datę
            for (int i = 0; i < chart.getData().size(); i++) {
                entries.add(new Entry(i,
                        chartData.get(i).getHigh()));
            }

            LineDataSet lds = new LineDataSet(entries, "ChartData");
            lds.setVisible(true);
            lds.setColors(Color.rgb(80,80,80));
            lds.setDrawFilled(true);
            lds.setFillColor(Color.BLUE);
            lds.setValueTextColor(Color.BLACK);

            LineData lineData = new LineData(lds);
            view.showChart(lineData);

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

    @Override
    public String getFiatTicker() {
        return fiatTicker;
    }

    @Override
    public String getCryptoTicker() {
        return cryptoTicker;
    }


    @Override
    public void orderBookPressed() {
        view.launchOrderBook();
    }

    @Override
    public void lowPressed() {
        view.showLowDialog();
    }

    @Override
    public void volumePressed() {
        view.showVolumeDialog();
    }

    @Override
    public void highPressed() {
        view.showHighDialog();
    }

    @Override
    public void currentPricePressed() {
        view.showCurrentPriceDialog();
    }
}
