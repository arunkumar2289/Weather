package com.wipro.weather.Networking;


public class Constants {

    private static String BASE_URL = "https://simple-weather.p.mashape.com/";

    private static String TYPE_TEXT_PLAIN = "text/plain";

    private static String WEATHER_FILE_NAME = "weather_data";

    public static Constants INSTANCE;

    private static String APIKEY = "WEP6VzIyZrmsh3kYGpQzjwCG6J1Bp1ojvFEjsnDfg9gasYpWTQ";

    public final String getBASE_URL() {
        return BASE_URL;
    }

    public final String getAPIKEY() {
        return APIKEY;
    }


    public final String getTYPE_TEXT_PLAIN() {
        return TYPE_TEXT_PLAIN;
    }


    public final String getWEATHER_FILE_NAME() {
        return WEATHER_FILE_NAME;
    }

    static {
        new Constants();
    }

    private Constants() {
        INSTANCE = this;
        BASE_URL = "https://simple-weather.p.mashape.com/";
        TYPE_TEXT_PLAIN = "text/plain";
        WEATHER_FILE_NAME = "weather_data";
    }

}
