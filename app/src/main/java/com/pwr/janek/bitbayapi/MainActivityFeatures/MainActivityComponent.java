package com.pwr.janek.bitbayapi.MainActivityFeatures;

import com.pwr.janek.bitbayapi.MainActivity;

import dagger.Component;

/*
    Component umożliwiający wstrzykowanie instancji do MainActivity.
    Umożliwia tworzenie instancji zdefiniowanych w MainActictivityModule (adapter, presenter)
 */

@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);

}
