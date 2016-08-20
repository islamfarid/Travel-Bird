package com.example.islam.travelbird.weather.view;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.example.islam.travelbird.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by islam on 8/20/16.
 */
public class WeatherFragmentTest {
    @Rule
    public ActivityTestRule<WeatherActivity> activityRule = new ActivityTestRule<>(
            WeatherActivity.class,
            true,     // initialTouchMode
            false);   // launchActivity. False so we can customize the intent per test method

    @Before
    public void setUp() {
        activityRule.launchActivity(new Intent());
    }

    @Test
    public void checkForecastsRecyclerViewISDisplayed() {
        onView(withId(R.id.weather_forecasts_recyclerview)
        ).check(matches(isDisplayed()));
    }

}