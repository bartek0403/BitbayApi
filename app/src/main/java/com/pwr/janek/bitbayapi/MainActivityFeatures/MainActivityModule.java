package com.pwr.janek.bitbayapi.MainActivityFeatures;

import dagger.Provides;

public class MainActivityModule {

   private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public BitbayOrderBookAdapter bitbayOrderBookAdapter(){
        return new BitbayOrderBookAdapter();
    }

}
