package com.wipro.weather.Utils;

import com.wipro.weather.R;

public class WeatherToImage {

    public static int getImageForCode(String code) {

        switch (code) {
            case "1":
                return R.drawable.clouds_with_lighting_littlerain_01;
            case "4":
                return R.drawable.clouds_with_lighting_01;
            case "28":
                return R.drawable.sun_with_3clouds_01;
            case "30":
                return R.drawable.sun_with_1cloud_01;
            case "31":
            case "33":
                return R.drawable.moon_01;
            case "32":
            case "34":
            case "36":
                return R.drawable.sun_01;
            case "45":
                return R.drawable.clouds_with_lighting_rain_01;
            case "26":
            case "27":
            case "29":
            case "44":
                return R.drawable.clouds_01;
            case "3":
            case "37":
            case "38":
            case "39":
                return R.drawable.clouds_with_2lighting_01;
            case "5":
            case "6":
            case "7":
            case "13":
            case "14":
            case "15":
            case "16":
            case "18":
            case "41":
            case "42":
            case "43":
            case "46":
                return R.drawable.clouds_with_snow_01;
            case "10":
            case "11":
            case "12":
            case "17":
            case "35":
            case "40":
            case "47":
                return R.drawable.clouds_with_rain_01;
            default:
                return R.drawable.ic_icon;
        }
    }

}
