package com.wipro.weather.Models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class WeatherData implements Serializable{
    @Expose private Query query;

    public Query getQuery() {
        return query;
    }


    public void setQuery(Query query) {
        this.query = query;
    }
}
