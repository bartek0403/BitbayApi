package com.pwr.janek.bitbayapi;

import android.app.Activity;
import android.app.Application;

public class BitbayOrderBookApp extends Application {

    private BitbayOrderBookComponent bitbayOrderBookAppComponent;

    private static BitbayOrderBookApp get(Activity activity) {
        return (BitbayOrderBookApp) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        BitbayOrderBookComponent = DaggerBitbayOrderBookComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

    }

}
