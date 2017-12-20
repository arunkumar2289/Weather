package com.wipro.weather.Models;

import java.io.Serializable;


public class Results implements Serializable{
    private  Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
