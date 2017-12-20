package com.wipro.weather.HomePackage.dagger;

import com.wipro.weather.HomePackage.MainActivity;

import dagger.Component;

@MainScope
@Component(modules = {MainContextModule.class, MainModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
