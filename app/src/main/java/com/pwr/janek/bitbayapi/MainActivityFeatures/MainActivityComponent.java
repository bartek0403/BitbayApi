package com.pwr.janek.bitbayapi.MainActivityFeatures;

import com.pwr.janek.bitbayapi.BitbayOrderBookApi;
import com.pwr.janek.bitbayapi.BitbayOrderBookComponent;

import dagger.Component;

@MainActivityScope
@Component(dependencies = BitbayOrderBookComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    BitbayOrderBookApi getBitbayOrderBook();
}
