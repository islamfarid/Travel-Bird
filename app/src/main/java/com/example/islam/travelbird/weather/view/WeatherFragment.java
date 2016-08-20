package com.example.islam.travelbird.weather.view;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.islam.travelbird.R;
import com.example.islam.travelbird.application.TravelBirdApplication;
import com.example.islam.travelbird.models.Forecast;
import com.example.islam.travelbird.weather.presenter.WeatherPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by islam on 8/19/16.
 */
public class WeatherFragment extends Fragment implements WeatherView {
    @Bind(R.id.weather_forecasts_recyclerview)
    RecyclerView mWeatherForecastsList;
    @Bind(R.id.progressBar)
    ProgressBar loadingBar;
    @Inject
    WeatherPresenter weatherPresenter;

    public WeatherFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        mWeatherForecastsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        attachPresenter();
        return view;
    }


    private void attachPresenter() {
        TravelBirdApplication.getInstance().getApplicationComponent().inject(this);
        weatherPresenter.setView(this);
        weatherPresenter.loadWeatherForecasts();
    }

    @Override
    public void hideProgress() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showForecastsLoadedMessage() {
        Snackbar.make(mWeatherForecastsList, getResources().getString(R.string.forecasts_loaded_successfully), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(mWeatherForecastsList, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setForecasts(List<Forecast> forecasts) {
        WeatherAdapter weatherAdapter = new WeatherAdapter(getActivity(), forecasts);
        mWeatherForecastsList.setAdapter(weatherAdapter);
    }


}
