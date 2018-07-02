package com.pwr.janek.bitbayapi.ApplicationDI;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppContextModule {

    private Context context;

    public AppContextModule (Context context){
    this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return context;
    }
}
