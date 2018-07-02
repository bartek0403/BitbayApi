package com.pwr.janek.bitbayapi.OrderBookFeatures.FragmentsDI;

import com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookMVP.Presenter;

import dagger.Module;
import dagger.Provides;
@Module
public class FragmentModule {

    @Provides
    public Presenter getPresenter(){
        return new Presenter();
    }


}
