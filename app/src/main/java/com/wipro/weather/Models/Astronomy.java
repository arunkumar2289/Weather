package com.wipro.weather.Models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Astronomy implements Serializable {
    @Expose
    private String sunrise;
    @Expose
    private String sunset;

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
