package com.wipro.weather.Models;


import java.io.Serializable;

public class Guid implements Serializable {

    private String isPermaLink;
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
