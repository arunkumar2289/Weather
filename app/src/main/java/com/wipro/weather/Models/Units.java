package com.wipro.weather.Models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;


public class Units implements Serializable {
    @Expose
    private String distance;
    @Expose
    private String pressure;
    @Expose
    private String speed;
    @Expose
    private String temperature;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
