package com.pwr.janek.bitbayapi.MainActivityFeatures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pwr.janek.bitbayapi.BitbayOrderBookComponent;
import com.pwr.janek.bitbayapi.ContextModule;
import com.pwr.janek.bitbayapi.DaggerBitbayOrderBookComponent;
import com.pwr.janek.bitbayapi.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BitbayOrderBookComponent daggerOrderBookComponent = DaggerBitbayOrderBookComponent
                .builder()
                .contextModule(new ContextModule(this))
                .build();
        daggerOrderBookComponent.getBitbayOrderBook();

    }
}
