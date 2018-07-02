package com.pwr.janek.bitbayapi.FragmentFeatures;

import com.pwr.janek.bitbayapi.MVP.Presenter;

import dagger.Module;
import dagger.Provides;
@Module
public class FragmentModule {

    @Provides
    public Presenter getPresenter(){
        return new Presenter();
    }


}
