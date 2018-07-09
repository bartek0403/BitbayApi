package com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityMVP;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
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
            Call<DailyChart> dailyChartCall = chartApi.getDailyChart("https://min-api.cryptocompare.com/data/histoday?fsym=BTC&tsym=USD&limit=20&e=Bitbay");
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
            List<CandleEntry> candleEntries = new ArrayList<CandleEntry>();

            for (int i = 0; i < chart.getData().size(); i++) {
                candleEntries.add(new CandleEntry(i,
                        chartData.get(i).getHigh(),
                        chartData.get(i).getLow(),
                        chartData.get(i).getOpen(),
                        chartData.get(i).getClose()));
            }

            CandleDataSet cds = new CandleDataSet(candleEntries, "ChartData");
            cds.setVisible(true);
            cds.setColors(ColorTemplate.VORDIPLOM_COLORS);
            cds.setShadowColor(Color.DKGRAY);
            cds.setShadowWidth(0.7f);
            cds.setDecreasingColor(Color.RED);
            cds.setDecreasingPaintStyle(Paint.Style.FILL_AND_STROKE);
            cds.setIncreasingColor(Color.GREEN);
            cds.setIncreasingPaintStyle(Paint.Style.FILL_AND_STROKE);
            cds.setNeutralColor(Color.BLUE);
            cds.setValueTextColor(Color.RED);

            CandleData candleData = new CandleData(cds);
            view.showChart(candleData);

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
}
