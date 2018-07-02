package com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookDI;

import com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookActivity;

import dagger.Component;

/*
    Component umożliwiający wstrzykowanie instancji do MainActivity.
    Umożliwia tworzenie instancji zdefiniowanych w MainActictivityModule (adapter, presenter)
 */

@Component(modules = OrderBookActivityModule.class)
public interface OrderBookActivityComponent {
    void inject(OrderBookActivity orderBookActivity);

}
