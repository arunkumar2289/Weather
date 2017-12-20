package com.wipro.weather.Models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Condition implements Serializable {
    @Expose
    private String code;
    @Expose
    private String date;
    @Expose
    private String temp;
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

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
