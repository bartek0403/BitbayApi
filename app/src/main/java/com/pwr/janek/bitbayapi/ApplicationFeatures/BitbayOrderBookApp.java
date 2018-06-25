package com.pwr.janek.bitbayapi.ApplicationFeatures;

import android.app.Activity;
import android.app.Application;

import com.pwr.janek.bitbayapi.BitbayOrderBookFeatures.BitbayOrderBookComponent;
import com.pwr.janek.bitbayapi.BitbayOrderBookFeatures.BitbayOrderBookModule;
import com.pwr.janek.bitbayapi.BitbayOrderBookFeatures.DaggerBitbayOrderBookComponent;


/*
    Klasa przechowująca instancje, których cykl życia równy aplikacji.
    Wymaga dodania w manifescie "android.name = ApplicationFeatures.BitbayOrderBookApp"
 */
public class BitbayOrderBookApp extends Application {

    private BitbayOrderBookComponent bitbayOrderBookAppComponent;

    public BitbayOrderBookComponent getBitbayOrderBookAppComponent() {
        return bitbayOrderBookAppComponent;
    }



    public static BitbayOrderBookApp get(Activity activity) {
        return (BitbayOrderBookApp) activity.getApplication();
    }

        @Override
        public void onCreate () {
            super.onCreate();

            bitbayOrderBookAppComponent = DaggerBitbayOrderBookComponent
                    .builder()
                    .bitbayOrderBookModule(new BitbayOrderBookModule())
                    .build();

        }

    }

