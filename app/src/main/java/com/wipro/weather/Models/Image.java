package com.wipro.weather.Models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;


public class Image implements Serializable {
    @Expose
    private  String title;
    @Expose
    private  String width;
    @Expose
    private  String height;
    @Expose
    private  String link;
    @Expose
    private  String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
