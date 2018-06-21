package com.pwr.janek.bitbayapi.BitbayOrderBookFeatures;

import com.pwr.janek.bitbayapi.ApiInterface.BitbayOrderBookApi;

import dagger.Component;

/*
 * Interfejs zwracający API
 * Component dla modułu BitbayOrderBookModule
 */

@BitbayOrderBookScope
@Component(modules = BitbayOrderBookModule.class)
public interface BitbayOrderBookComponent  {
    BitbayOrderBookApi getBitbayOrderBook();
}
