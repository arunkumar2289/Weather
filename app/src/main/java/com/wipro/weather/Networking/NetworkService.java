package com.wipro.weather.Networking;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public final class NetworkService {

    Retrofit retrofit;
    public static NetworkService INSTANCE;

    private NetworkService() {
        INSTANCE = this;
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.INSTANCE.getBASE_URL())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    static {
        new NetworkService();
    }


    public final MetaWeatherApi getMetaWeatherApi() {
        return retrofit.create(MetaWeatherApi.class);
    }
}
