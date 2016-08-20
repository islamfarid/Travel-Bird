package com.example.islam.travelbird.network;

import com.example.islam.travelbird.models.QueryWeather;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by islam on islam on 8/19/16.
 */
public interface NetworkInterface {
    @GET("")
    Observable<QueryWeather> getWeatherData(@Url String absoluteURL);
}
