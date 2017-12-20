package com.wipro.weather.Models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {
    @Expose
    private String title;
    @Expose
    private String lat;
    @Expose
    private String lng;

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Expose
    private String link;
    @Expose
    private String pubDate;
    @Expose
    private String description;
    @Expose
    private Condition condition;
    @Expose
    private List<Forecast> forecast;
    @Expose
    private Guid guid;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public Guid getGuid() {
        return guid;
    }

    public void setGuid(Guid guid) {
        this.guid = guid;
    }
}
