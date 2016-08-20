package com.example.islam.travelbird.weather.bussiness;

import com.example.islam.travelbird.application.TravelBirdApplication;
import com.example.islam.travelbird.common.Constants;
import com.example.islam.travelbird.models.Forecast;
import com.example.islam.travelbird.network.NetworkInterface;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by islam on 8/19/16.
 */
public class WeatherBussiness {
    @Inject
    NetworkInterface networkInterface;

    @Inject
    public WeatherBussiness() {
        TravelBirdApplication.getInstance().getApplicationComponent().inject(this);
    }

    public Observable<List<Forecast>> getWeatherForcats() {
        return networkInterface.getWeatherData(Constants.YAHOO_WEATHER_ABSOLUTE_URL).map(
                queryWeather -> queryWeather.getQuery().getResults().getChannel().getItem()
                        .getForecast());
    }
}
