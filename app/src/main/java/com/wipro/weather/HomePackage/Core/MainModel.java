package com.wipro.weather.HomePackage.Core;

import com.wipro.weather.HomePackage.MainActivity;
import com.wipro.weather.Models.WeatherData;
import com.wipro.weather.Networking.MetaWeatherApi;

public class MainModel {

    private MetaWeatherApi api;
    private MainActivity splashContext;

    public MainModel(MetaWeatherApi api, MainActivity splashCtx) {
        this.api = api;
        this.splashContext = splashCtx;

    }

}
