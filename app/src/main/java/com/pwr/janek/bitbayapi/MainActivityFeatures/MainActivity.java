package com.pwr.janek.bitbayapi.MainActivityFeatures;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleData;
import com.pwr.janek.bitbayapi.FontHelper;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityDI.ContextModule;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityDI.DaggerMainActivityComponent;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityDI.MainActivityComponent;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityDI.MainActivityModule;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityMVP.MVPMainActivityContract;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityMVP.Presenter;
import com.pwr.janek.bitbayapi.Model.Ticker;
import com.pwr.janek.bitbayapi.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

//TODO: Połączyć MainActivity z OrderBookActivity
//TODO: Poprawić layout

public class MainActivity extends AppCompatActivity implements MVPMainActivityContract.View {

    @BindView(R.id.chart)
    CandleStickChart chart;

    @BindView(R.id.button_refresh)
    TextView refresh;
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;

    @BindView(R.id.spiner_crypto)
    Spinner spinner_crypto;

    @BindView(R.id.spiner_fiat)
    Spinner spinner_fiat;

    @BindView(R.id.textView_current)
    TextView current;

    @BindView(R.id.textView_high)
    TextView high;

    @BindView(R.id.textView_low)
    TextView low;

    @BindView(R.id.textView_volume)
    TextView volume;

    @Inject
    Presenter presenter;

    @Inject
    SharedPreferences.Editor sharedPreferencesEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule())
                .contextModule(new ContextModule(this))
                .build();
        mainActivityComponent.inject(this);

        ArrayAdapter<String> arrayAdapter_crypto = new ArrayAdapter<String>(MainActivity.this,
                R.layout.spiner_layout,
                getResources().getStringArray(R.array.CryptoTickers));
        ArrayAdapter<String> arrayAdapter_fiat = new ArrayAdapter<String>(MainActivity.this,
                R.layout.spiner_layout,
                getResources().getStringArray(R.array.FiatTickers));
        spinner_fiat.setAdapter(arrayAdapter_fiat);
        spinner_crypto.setAdapter(arrayAdapter_crypto);

        presenter.setView(this);
        presenter.fetchData();

        Typeface iconFont = FontHelper.getTypeface(getApplicationContext(), FontHelper.FONTAWESOME);
        FontHelper.markAsIconContainer(findViewById(R.id.icon_currentPrice), iconFont);
        FontHelper.markAsIconContainer(findViewById(R.id.icon_high), iconFont);
        FontHelper.markAsIconContainer(findViewById(R.id.icon_low), iconFont);
        FontHelper.markAsIconContainer(findViewById(R.id.icon_volume), iconFont);

    }



    @Override
    public void showData(Ticker ticker) {
        if(ticker != null) {
            current.setText(String.valueOf(ticker.getLast()) + " " + presenter.getFiatTicker() );
            high.setText(String.valueOf(ticker.getMax()) + " " +presenter.getFiatTicker());
            low.setText(String.valueOf(ticker.getMin()) + " " + presenter.getFiatTicker());
            volume.setText(String.format("%.2f" ,ticker.getVolume()) + " " + presenter.getCryptoTicker());
        }


    }

    @Override
    public void showFailureMsg(String msg) {
        Toast.makeText(this, msg ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showChart(CandleData candleData) {
            this.chart.setData(candleData);
            this.chart.getXAxis().setDrawLabels(false);
            this.chart.animateXY(500,500);
            this.chart.invalidate();
    }

    @OnClick(R.id.button_refresh)
    void refresh(){
        presenter.fetchData();
    }

    @OnItemSelected(R.id.spiner_fiat)
    void onFiatSelected(int position){
        presenter.saveFiatTicker(spinner_fiat.getItemAtPosition(position).toString());
    }

    @OnItemSelected(R.id.spiner_crypto)
    void onCryptoSelected(int position){
        presenter.saveCryptoTicker(spinner_crypto.getItemAtPosition(position).toString());
    }
}
