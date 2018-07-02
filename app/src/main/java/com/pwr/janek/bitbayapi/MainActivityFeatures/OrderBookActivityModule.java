package com.pwr.janek.bitbayapi.MainActivityFeatures;

import com.pwr.janek.bitbayapi.AskFragment;
import com.pwr.janek.bitbayapi.BidFragment;
import com.pwr.janek.bitbayapi.OrderBookActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class OrderBookActivityModule {

    OrderBookActivity activity;

    public OrderBookActivityModule(OrderBookActivity activity){
        this.activity = activity;
    }

    @Provides
    public android.support.v4.app.FragmentManager getFragmentManager(){
        return activity.getSupportFragmentManager();
    }

    @Provides
    public BidFragment getBidFragment(){
        return new BidFragment();
    }

    @Provides
    public AskFragment getAskFragment(){
        return new AskFragment();
    }

}
