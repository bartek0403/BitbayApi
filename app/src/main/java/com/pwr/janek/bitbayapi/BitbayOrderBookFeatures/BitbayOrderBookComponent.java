package com.pwr.janek.bitbayapi.BitbayOrderBookFeatures;

import com.pwr.janek.bitbayapi.ApiInterface.BitbayOrderBookApi;

import javax.inject.Singleton;

import dagger.Component;

/*
 * Interfejs zwracający API
 * Component dla modułu BitbayOrderBookModule
 */

@Singleton
@Component(modules = BitbayOrderBookModule.class)
public interface BitbayOrderBookComponent  {
    BitbayOrderBookApi getBitbayOrderBook();
}
