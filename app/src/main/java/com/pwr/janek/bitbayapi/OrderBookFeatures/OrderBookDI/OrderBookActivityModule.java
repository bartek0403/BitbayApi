package com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookDI;

import com.pwr.janek.bitbayapi.OrderBookFeatures.AskFragment;
import com.pwr.janek.bitbayapi.OrderBookFeatures.BidFragment;
import com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookActivity;

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
