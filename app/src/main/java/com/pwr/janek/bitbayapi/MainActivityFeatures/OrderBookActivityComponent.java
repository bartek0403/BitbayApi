package com.pwr.janek.bitbayapi.MainActivityFeatures;

import com.pwr.janek.bitbayapi.OrderBookActivity;

import dagger.Component;

/*
    Component umożliwiający wstrzykowanie instancji do MainActivity.
    Umożliwia tworzenie instancji zdefiniowanych w MainActictivityModule (adapter, presenter)
 */

@Component(modules = OrderBookActivityModule.class)
public interface OrderBookActivityComponent {
    void inject(OrderBookActivity orderBookActivity);

}
