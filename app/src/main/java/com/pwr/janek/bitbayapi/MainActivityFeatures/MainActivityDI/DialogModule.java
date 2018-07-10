package com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityDI;

import android.app.AlertDialog;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class DialogModule {

    @Provides
    AlertDialog.Builder getDialogBuilder(Context context){
        return new AlertDialog.Builder(context);

    }
}
