package com.pwr.janek.bitbayapi.FragmentFeatures;

import com.pwr.janek.bitbayapi.AskFragment;
import com.pwr.janek.bitbayapi.BidFragment;

import dagger.Component;

@Component(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(BidFragment bidFragment);
    void inject (AskFragment askFragment);

}
