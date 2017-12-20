package com.wipro.weather.Models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;


public class Location implements Serializable {
    @Expose
    private String city;
    @Expose
    private String country;
    @Expose
    private String region;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
