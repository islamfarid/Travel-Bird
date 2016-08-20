package com.example.islam.travelbird.common;

/**
 * Created by islam on 8/19/16.
 */
public class Constants {
    public static String YAHOO_WEATHER_ABSOLUTE_URL ="https://query.yahooapis.com/v1/public/yql?q=se" +
            "lect%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo." +
            "places(1)%20where%20text%3D%22amsterdam%22)&format=json&env=store%3A%2F%2Fdatatables.o" +
            "rg%2Falltableswithkeys";
}
