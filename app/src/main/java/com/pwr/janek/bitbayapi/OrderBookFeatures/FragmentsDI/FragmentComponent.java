package com.pwr.janek.bitbayapi.OrderBookFeatures.FragmentsDI;

import com.pwr.janek.bitbayapi.OrderBookFeatures.AskFragment;
import com.pwr.janek.bitbayapi.OrderBookFeatures.BidFragment;

import dagger.Component;

@Component(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(BidFragment bidFragment);
    void inject (AskFragment askFragment);

}
