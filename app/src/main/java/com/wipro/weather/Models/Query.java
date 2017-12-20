package com.wipro.weather.Models;


import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Query implements Serializable {
    @Expose
    private int count;
    @Expose
    private String created;
    @Expose
    private String lang;
    @Expose
    private Results results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
