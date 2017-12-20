package com.wipro.weather.Models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;


public class Results implements Serializable{
    @Expose
    private  Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
