package com.pwr.janek.bitbayapi.MainActivityFeatures;

import com.pwr.janek.bitbayapi.Adapter.BitbayOrderBookAdapter;
import com.pwr.janek.bitbayapi.MVP.Presenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    public Presenter getPresenter(){
        return new Presenter();
    }

    @Provides
    public BitbayOrderBookAdapter bitbayOrderBookAdapter(){
        return new BitbayOrderBookAdapter();
    }

}
