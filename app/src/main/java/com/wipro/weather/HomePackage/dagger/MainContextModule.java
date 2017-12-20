package com.wipro.weather.HomePackage.dagger;

import com.wipro.weather.HomePackage.MainActivity;

import dagger.Module;
import dagger.Provides;
@Module
public class MainContextModule {

    MainActivity splashContext;

    public MainContextModule(MainActivity context) {
        this.splashContext = context;
    }

    @MainScope
    @Provides
    MainActivity provideSplashContext() {
        return splashContext;
    }


}
