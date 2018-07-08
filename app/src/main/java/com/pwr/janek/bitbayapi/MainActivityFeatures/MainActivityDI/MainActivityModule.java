package com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityDI;

import android.content.Context;
import android.content.SharedPreferences;

import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityMVP.Presenter;
import com.pwr.janek.bitbayapi.R;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    Presenter getPresenter(){
        return new Presenter();
    }

    @Provides
    SharedPreferences.Editor getSharedPref(Context context){
        return context.getSharedPreferences(context.getString(R.string.prefkey),Context.MODE_PRIVATE).edit();

    }
}
