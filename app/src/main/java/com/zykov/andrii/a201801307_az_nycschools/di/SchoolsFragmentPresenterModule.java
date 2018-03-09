package com.zykov.andrii.a201801307_az_nycschools.di;

import android.app.Application;

import com.zykov.andrii.a201801307_az_nycschools.presenter.SchoolDetailsPresenterImpl;
import com.zykov.andrii.a201801307_az_nycschools.presenter.SchoolsPresenterImpl;
import com.zykov.andrii.a201801307_az_nycschools.utils.nycschoolservice.NYCSchoolsAPI;
import com.zykov.andrii.a201801307_az_nycschools.view.SchoolDetailsFragment;
import com.zykov.andrii.a201801307_az_nycschools.view.SchoolsFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrii on 3/8/18.
 */

@Module
public class SchoolsFragmentPresenterModule {

    @Singleton
    @Provides
    public SchoolsFragment.ISchoolsFragmentPresenter providePresenter(NYCSchoolsAPI nycSchoolsAPI, Application application){
        return new SchoolsPresenterImpl(nycSchoolsAPI, application);
    }

}
