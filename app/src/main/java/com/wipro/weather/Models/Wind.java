package com.wipro.weather.Models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;


public class Wind implements Serializable {
    @Expose
    private String chill;
    @Expose
    private String direction;
    @Expose
    private String speed;

    public String getChill() {
        return chill;
    }

    public void setChill(String chill) {
        this.chill = chill;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
