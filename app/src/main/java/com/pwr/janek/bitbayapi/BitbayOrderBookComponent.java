package com.pwr.janek.bitbayapi;

import dagger.Component;

@BitbayOrderBookScope
@Component(modules = BitbayOrderBookModule.class)
public interface BitbayOrderBookComponent  {
    BitbayOrderBookApi getBitbayOrderBook();
}
