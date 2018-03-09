package com.zykov.andrii.a201801307_az_nycschools;

import android.app.Application;

import com.zykov.andrii.a201801307_az_nycschools.di.AppComponent;
import com.zykov.andrii.a201801307_az_nycschools.di.AppModule;
import com.zykov.andrii.a201801307_az_nycschools.di.DaggerAppComponent;
import com.zykov.andrii.a201801307_az_nycschools.di.NetworkModule;
import com.zykov.andrii.a201801307_az_nycschools.di.SchoolDetailsPresenterModule;
import com.zykov.andrii.a201801307_az_nycschools.di.SchoolsFragmentPresenterModule;

/**
 * Created by andrii on 3/8/18.
 */

public class SchoolsApplication extends Application {

    private String BASE_URL = "https://data.cityofnewyork.us/resource/";

    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BASE_URL))
                .schoolsFragmentPresenterModule(new SchoolsFragmentPresenterModule())
                .schoolDetailsPresenterModule(new SchoolDetailsPresenterModule())
                .build();
    }
}
