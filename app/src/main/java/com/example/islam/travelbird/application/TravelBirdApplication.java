package com.example.islam.travelbird.application;

import android.app.Application;

import com.example.islam.travelbird.dagger.ApplicationComponent;
import com.example.islam.travelbird.dagger.ApplicationModule;
import com.example.islam.travelbird.dagger.DaggerApplicationComponent;

/**
 * Created by islam on 8/19/16.
 */
public class TravelBirdApplication extends Application {
    ApplicationComponent applicationComponent;
    private static TravelBirdApplication travelBirdApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        this.travelBirdApplication = this;
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    /**
     * we are sure that this instance wont be null in our application
     * as we give it a value in the application onCreate.
     * @return
     */
    public static TravelBirdApplication getInstance() {
        return travelBirdApplication;
    }
}

