package com.example.islam.travelbird.weather.view;


import com.example.islam.travelbird.models.Forecast;

import java.util.List;

/**
 * Created by islam on 8/19/16.
 */
public interface WeatherView {
    void showProgress();
    void showForecastsLoadedMessage();
    void showErrorMessage(String message);
    void setForecasts(List<Forecast> forecasts);
    void hideProgress();
}
