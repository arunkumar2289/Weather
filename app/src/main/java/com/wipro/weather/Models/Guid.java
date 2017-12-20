package com.wipro.weather.Models;


import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Guid implements Serializable {
    @Expose
    private String isPermaLink;
    @Expose
    private String content;


    public String getIsPermaLink() {
        return isPermaLink;
    }

    public void setIsPermaLink(String isPermaLink) {
        this.isPermaLink = isPermaLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
