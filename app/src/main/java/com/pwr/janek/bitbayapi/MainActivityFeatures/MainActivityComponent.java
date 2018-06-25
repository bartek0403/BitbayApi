package com.pwr.janek.bitbayapi.MainActivityFeatures;

import com.pwr.janek.bitbayapi.BitbayOrderBookFeatures.BitbayOrderBookComponent;
import com.pwr.janek.bitbayapi.MVP.Presenter;
import com.pwr.janek.bitbayapi.MainActivity;

import dagger.Component;

/*
    Component umożliwiający wstrzykowanie instancji do MainActivity.
    Umożliwia tworzenie instancji zdefiniowanych w MainActictivityModule (BitbayOrderBookAdapter)
    oraz instancji zdefiniowanych w BitbayOrderBookComponent (BitbayOrderBookApi)
 */

@MainActivityScope
@Component(dependencies = BitbayOrderBookComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void injectMainActivity(MainActivity mainActivity);
    void injectPresenter (Presenter presenter);
}
