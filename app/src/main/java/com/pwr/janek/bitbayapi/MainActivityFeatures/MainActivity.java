package com.pwr.janek.bitbayapi.MainActivityFeatures;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineData;
import com.pwr.janek.bitbayapi.FontHelper;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityDI.ContextModule;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityDI.DaggerMainActivityComponent;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityDI.MainActivityComponent;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityDI.MainActivityModule;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityMVP.MVPMainActivityContract;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityMVP.Presenter;
import com.pwr.janek.bitbayapi.Model.Ticker;
import com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookActivity;
import com.pwr.janek.bitbayapi.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

//TODO: Poprawić layout


public class MainActivity extends AppCompatActivity implements MVPMainActivityContract.View {

    // ICONS
    @BindView(R.id.icon_orderBook)
    TextView orderBook;

    // TEXTVIEWS
    @BindView(R.id.textView_current)
    TextView current;
    @BindView(R.id.textView_high)
    TextView high;
    @BindView(R.id.textView_low)
    TextView low;
    @BindView(R.id.textView_volume)
    TextView volume;

    // CHART
    @BindView(R.id.chart)
    LineChart chart;

    // HEADER (TOOLBAR, SPINNERS & BUTTONS
    @BindView(R.id.button_refresh)
    TextView refresh;
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.spiner_crypto)
    Spinner spinner_crypto;
    @BindView(R.id.spiner_fiat)
    Spinner spinner_fiat;

    // INJECTION
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
        FontHelper.markAsIconContainer(findViewById(R.id.icon_orderBook), iconFont);

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

    // INTERNAL METHODS
    @Override
    public void showFailureMsg(String msg) {
        Toast.makeText(this, msg ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showChart(LineData lineData) {
            this.chart.setData(lineData);
            this.chart.getXAxis().setDrawLabels(false);
            this.chart.animateXY(500,500);
            Description d = new Description();
            d.setText("");
            this.chart.setDescription(d);
            this.chart.getLegend().setEnabled(false);
            this.chart.invalidate();
    }

    @Override
    public void launchOrderBook() {
        Intent i = new Intent(this, OrderBookActivity.class);
        ActivityOptions activityOptions = ActivityOptions
                .makeSceneTransitionAnimation(this, orderBook, getString(R.string.transition));
        startActivity(i, activityOptions.toBundle());
    }

    @Override
    public void showCurrentPriceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.currentPriceTitle);
        builder.setMessage(R.string.currentPriceDialog);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showLowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.lowPriceTitle);
        builder.setMessage(R.string.lowPriceDialog);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showVolumeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.volumeTitle);
        builder.setMessage(R.string.volumeDialog);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showHighDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.maxPriceTitle);
        builder.setMessage(R.string.maxPriceDialog);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // ONCLICKS
    @OnClick(R.id.icon_high)
    void highPressed(){
        presenter.highPressed();
    }
    @OnClick(R.id.button_refresh)
    void refresh(){
        presenter.fetchData();
    }

    @OnClick(R.id.icon_currentPrice)
    void currenPricePressed(){
        presenter.currentPricePressed();
    }

    @OnClick(R.id.icon_low)
    void lowPressed(){
        presenter.lowPressed();
    }

    @OnClick(R.id.icon_volume)
    void volumePressed(){
        presenter.volumePressed();
    }

    @OnClick(R.id.icon_orderBook)
    void orderBookPressed(){
        presenter.orderBookPressed();
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
