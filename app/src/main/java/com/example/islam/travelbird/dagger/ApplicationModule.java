package com.example.islam.travelbird.dagger;

import com.example.islam.travelbird.application.TravelBirdApplication;
import com.example.islam.travelbird.network.ServiceGenerator;
import com.example.islam.travelbird.network.NetworkInterface;
import com.example.islam.travelbird.weather.bussiness.WeatherBussiness;
import com.example.islam.travelbird.weather.presenter.WeatherPresenter;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 8/19/16.
 */
@Module
public class ApplicationModule {
    //if any provided component needs the context like Database or sharedpreference
    //we will use this
    TravelBirdApplication travelBirdApplication;

    public ApplicationModule(TravelBirdApplication travelBirdApplication) {
        this.travelBirdApplication = travelBirdApplication;
    }

    @Provides
    public WeatherPresenter provideWeatherPresenter() {
        return new WeatherPresenter();
    }

    @Provides
    public WeatherBussiness provideWeatherBussiness() {
        return new WeatherBussiness();
    }

    @Provides
    public NetworkInterface provideYAhooClient() {
        return ServiceGenerator.createService(NetworkInterface.class);
    }
}
