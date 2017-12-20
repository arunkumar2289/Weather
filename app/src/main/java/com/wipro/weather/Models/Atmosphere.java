package com.wipro.weather.Models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Atmosphere implements Serializable {
    @Expose
    private String humidity;
    @Expose
    private String pressure;
    @Expose
    private String rising;
    @Expose
    private String visibility;

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getRising() {
        return rising;
    }

    public void setRising(String rising) {
        this.rising = rising;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
