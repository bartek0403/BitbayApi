package com.pwr.janek.bitbayapi.ApplicationDI;


import com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookMVP.Presenter;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {ApiModule.class, AppContextModule.class})
public interface BitbayAppComponent {

    public void inject(Presenter presenter);
}
