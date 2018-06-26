package com.pwr.janek.bitbayapi.ApplicationFeatures;


import com.pwr.janek.bitbayapi.MVP.Presenter;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {ApiModule.class, AppContextModule.class})
public interface BitbayAppComponent {

    public void inject(Presenter presenter);
}
