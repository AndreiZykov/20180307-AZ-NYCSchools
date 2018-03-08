package com.zykov.andrii.a201801307_az_nycschools.di;

import com.zykov.andrii.a201801307_az_nycschools.view.SchoolDetailsFragment;
import com.zykov.andrii.a201801307_az_nycschools.view.SchoolsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrii on 3/8/18.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, SchoolsFragmentPresenterModule.class, SchoolDetailsPresenterModule.class})
public interface AppComponent {
    void inject(SchoolsFragment view);
    void inject(SchoolDetailsFragment view);
}
