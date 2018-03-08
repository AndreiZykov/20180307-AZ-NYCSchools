package com.zykov.andrii.a201801307_az_nycschools.di;

import com.zykov.andrii.a201801307_az_nycschools.presenter.SchoolDetailsPresenterImpl;
import com.zykov.andrii.a201801307_az_nycschools.view.SchoolDetailsFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrii on 3/8/18.
 */

@Module
public class SchoolDetailsPresenterModule {

    @Singleton
    @Provides
    public SchoolDetailsFragment.ISchoolDetailsPresenter providePresenter(){
        return new SchoolDetailsPresenterImpl();
    }

}
