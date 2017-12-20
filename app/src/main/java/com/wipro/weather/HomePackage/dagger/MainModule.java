package com.wipro.weather.HomePackage.dagger;

import com.wipro.weather.HomePackage.Core.MainModel;
import com.wipro.weather.HomePackage.Core.MainView;
import com.wipro.weather.HomePackage.MainActivity;
import com.wipro.weather.Networking.MetaWeatherApi;

import dagger.Module;
import dagger.Provides;


@Module
public class MainModule {

    @MainScope
    @Provides
    MainView provideSplashView(MainActivity context) {
        return new MainView(context);
    }

    @MainScope
    @Provides
    MainModel provideSplashModel(MetaWeatherApi api, MainActivity ctx) {
        return new MainModel(api, ctx);
    }

}

