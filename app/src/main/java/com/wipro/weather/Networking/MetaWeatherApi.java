package com.wipro.weather.Networking;

import com.wipro.weather.Models.WeatherData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MetaWeatherApi {

    @GET("weatherdata")
    Observable<WeatherData> getLocationDetails(@Header("X-Mashape-Key") String var1, @Header("Accept") String var2, @Query("lat") double var3, @Query("lng") double var5);
}
