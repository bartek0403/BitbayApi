package com.pwr.janek.bitbayapi.BitbayOrderBookFeatures;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/*
 * Moduł dostarczający CONTEXT APLIKACJI
 */

@Module
public class ContextModule {

        Context context;

        public ContextModule(Context context){
            this.context = context;
        }

        @Provides
        public Context context(){
            return context.getApplicationContext(); }

}
