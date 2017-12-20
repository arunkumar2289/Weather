package com.wipro.weather.Models;

import java.io.Serializable;

public class WeatherData implements Serializable{
    private Query query;

    public Query getQuery() {
        return query;
    }


    public void setQuery(Query query) {
        this.query = query;
    }
}
