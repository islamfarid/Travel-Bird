package com.example.islam.travelbird.weather.presenter;

import android.support.test.InstrumentationRegistry;

import com.example.islam.travelbird.models.Forecast;
import com.example.islam.travelbird.weather.bussiness.WeatherBussiness;
import com.example.islam.travelbird.weather.view.WeatherView;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.Observable;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by islam on 8/20/16.
 */
public class WeatherPresenterTest {
    WeatherBussiness weatherBussiness;
    WeatherView weatherView;
    WeatherPresenter weatherPresenter;

    @Before
    public void init() {
        System.setProperty("dexmaker.dexcache", InstrumentationRegistry.getInstrumentation().getTargetContext().getCacheDir().getPath());
        weatherPresenter = new WeatherPresenter();
        weatherBussiness = mock(WeatherBussiness.class);
        weatherView = mock(WeatherView.class);
        weatherPresenter.setView(weatherView);
        weatherPresenter.setWeatherBussiness(weatherBussiness);
    }

    @Test
    public void testWhenloadWeatherForecastsCalled_getWeatherForecatsInBussinessISCalled()  {
        doNothing().when(weatherView).showProgress();
        when(weatherBussiness.getWeatherForcats()).thenReturn(Observable.create((Observable.OnSubscribe<List<Forecast>>) sub -> {
        }));
        weatherPresenter.loadWeatherForecasts();
        verify(weatherBussiness, times(1)).getWeatherForcats();
    }
    @Test
    public void testWhenloadWeatherForecastsCalled_weatherViewShowProgressISCalled() {
        when(weatherBussiness.getWeatherForcats()).thenReturn(Observable.create((Observable.OnSubscribe<List<Forecast>>) sub -> {
            //do nothing we just test if method is called
        }));
        weatherPresenter.loadWeatherForecasts();
        verify(weatherView, times(1)).showProgress();
    }
    @Test
    public void testWhenloadWeatherForecastsError_weatherViewHideProgressISCalled()  {
        doNothing().when(weatherView).showProgress();
        when(weatherBussiness.getWeatherForcats()).thenReturn(Observable.create((Observable.OnSubscribe<List<Forecast>>) sub -> {
            sub.onError(new Throwable());
            verify(weatherView, times(1)).hideProgress();
        }));
        weatherPresenter.loadWeatherForecasts();
    }
    @Test
    public void testWhenloadWeatherForecastsCompleted_weatherViewHideProgressISCalled()  {
        doNothing().when(weatherView).showProgress();
        when(weatherBussiness.getWeatherForcats()).thenReturn(Observable.create((Observable.OnSubscribe<List<Forecast>>) sub -> {
            sub.onCompleted();
            verify(weatherView, times(1)).hideProgress();
        }));
        weatherPresenter.loadWeatherForecasts();
    }

}