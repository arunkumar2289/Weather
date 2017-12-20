package com.wipro.weather.HomePackage;

import android.content.Context;

import com.wipro.weather.Models.WeatherData;

public interface HomeContract {

     interface View {
         
        void onDataFetched(WeatherData var1);

        void onStoredDataFetched(WeatherData var1);

        void onError();

        Context getContext();
    }

     interface Presenter {
        void subscribe(HomeContract.View var1);

        void unSubscribe();

        void refresh(double var1, double var3);
    }
}
