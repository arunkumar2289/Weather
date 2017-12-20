package com.wipro.weather.Models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Forecast implements Serializable {
    @Expose
    private String code;
    @Expose
    private String date;
    @Expose
    private String day;
    @Expose
    private String high;
    @Expose
    private String low;
    @Expose
    private String text;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
