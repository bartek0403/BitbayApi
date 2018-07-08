package com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityDI;


import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivity;

import dagger.Component;

@Component(modules = {MainActivityModule.class, ContextModule.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
