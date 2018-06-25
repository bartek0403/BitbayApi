package com.pwr.janek.bitbayapi.MainActivityFeatures;

import com.pwr.janek.bitbayapi.Adapter.BitbayOrderBookAdapter;
import com.pwr.janek.bitbayapi.ApiInterface.BitbayOrderBookApi;
import com.pwr.janek.bitbayapi.MVP.Presenter;
import com.pwr.janek.bitbayapi.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public Presenter getPresenter(BitbayOrderBookApi bitbayOrderBookApi){
        return new Presenter(bitbayOrderBookApi);
    }

    @Provides
    @MainActivityScope
    public BitbayOrderBookAdapter bitbayOrderBookAdapter(){
        return new BitbayOrderBookAdapter();
    }

}
