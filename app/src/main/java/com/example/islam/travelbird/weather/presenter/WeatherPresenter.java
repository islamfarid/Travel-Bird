package com.example.islam.travelbird.weather.presenter;


import com.example.islam.travelbird.application.TravelBirdApplication;
import com.example.islam.travelbird.models.Forecast;
import com.example.islam.travelbird.weather.bussiness.WeatherBussiness;
import com.example.islam.travelbird.weather.view.WeatherView;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by islam on 8/19/16.
 */
public class WeatherPresenter {
    WeakReference<WeatherView> weatherView;
    @Inject
    WeatherBussiness weatherBussiness;

    public void setView(WeatherView weatherView) {
        TravelBirdApplication.getInstance().getApplicationComponent().inject(this);
        this.weatherView = new WeakReference(weatherView);
    }

    public void loadWeatherForecasts() {
        if (isViewAttached()){
            weatherView.get().showProgress();
        }
        weatherBussiness.getWeatherForcats().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(new Subscriber<List<Forecast>>() {
            @Override
            public void onCompleted() {
                if (isViewAttached()){
                    weatherView.get().hideProgress();
                    weatherView.get().showForecastsLoadedMessage();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()){
                    weatherView.get().hideProgress();
                    weatherView.get().showErrorMessage(e.getMessage());
                }
            }

            @Override
            public void onNext(List<Forecast> forecasts) {
                if (isViewAttached()){
                    weatherView.get().hideProgress();
                    weatherView.get().setForecasts(forecasts);
                }
            }
        });
    }

    private boolean isViewAttached() {
        return weatherView != null && weatherView.get() != null;
    }


    public void setWeatherBussiness(WeatherBussiness weatherBussiness) {
        this.weatherBussiness = weatherBussiness;
    }
}
