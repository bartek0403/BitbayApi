package com.pwr.janek.bitbayapi.ApplicationFeatures;

import android.app.Application;



/*
    Klasa przechowująca instancje, których cykl życia równy aplikacji (Context, api).
    Wymaga dodania w manifescie "android.name = ApplicationFeatures.BitbayApp"
 */
public class BitbayApp extends Application {

    private static BitbayAppComponent bitbayAppComponent;

    public BitbayAppComponent getBitbayAppComponent() {
        return bitbayAppComponent;
    }

    public static BitbayAppComponent getAppComponent() {
        return bitbayAppComponent;
    }

        @Override
        public void onCreate () {
            super.onCreate();

            bitbayAppComponent = DaggerBitbayAppComponent
                    .builder()
                    .apiModule(new ApiModule())
                    .appContextModule(new AppContextModule(this))
                    .build();

        }

    }

