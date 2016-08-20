package com.example.islam.travelbird.dagger;


import com.example.islam.travelbird.weather.bussiness.WeatherBussiness;
import com.example.islam.travelbird.weather.presenter.WeatherPresenter;
import com.example.islam.travelbird.weather.view.WeatherFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by islam on 8/19/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
   void inject(WeatherFragment weatherFragment);
   void inject(WeatherPresenter weatherPresenter);
   void inject(WeatherBussiness weatherBussiness);

}
