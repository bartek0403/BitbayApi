package com.pwr.janek.bitbayapi.MainActivityFeatures;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements MVPMainActivityContract.View {

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

    @BindView(R.id.textView_vwap)
    TextView vwap;

    @BindView(R.id.textView_average)
    TextView average;

    @BindView(R.id.texxtView_volume)
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
    }



    @Override
    public void showData(Ticker ticker) {
        if(ticker != null) {
            current.setText(String.valueOf(ticker.getLast()));
            high.setText((String.valueOf(ticker.getMax())));
            low.setText(String.valueOf(ticker.getMin()));
            vwap.setText(String.valueOf(ticker.getVwap()));
            average.setText(String.valueOf(ticker.getAverage()));
            volume.setText(String.valueOf(ticker.getVolume()));
        }


    }

    @Override
    public void showFailureMsg() {
        Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button_refresh)
    void refresh(){
        presenter.fetchData();
    }

    @OnItemSelected(R.id.spiner_fiat)
    void onFiatSelected(int position){
       // sharedPreferencesEditor.putString(getString(R.string.fiatTicker),
       //         String.valueOf(spinner_fiat.getItemAtPosition(position)));
       // sharedPreferencesEditor.commit();
        presenter.saveFiatTicker(spinner_fiat.getItemAtPosition(position).toString());
        Log.i("Spiner fiat", "onFiatSelected: " + spinner_fiat.getItemAtPosition(position).toString());
    }

    @OnItemSelected(R.id.spiner_crypto)
    void onCryptoSelected(int position){
       // sharedPreferencesEditor.putString(getString(R.string.cryptoTicker),
       //         String.valueOf(spinner_fiat.getItemAtPosition(position)));
        //sharedPreferencesEditor.commit();
        presenter.saveCryptoTicker(spinner_crypto.getItemAtPosition(position).toString());
        Log.i("spiner crypto", "onCryptoSelected: " + spinner_crypto.getItemAtPosition(position).toString());
    }
}
