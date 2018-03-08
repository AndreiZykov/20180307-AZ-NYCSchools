package com.zykov.andrii.a201801307_az_nycschools.presenter;

import com.zykov.andrii.a201801307_az_nycschools.data.SchoolWrapper;
import com.zykov.andrii.a201801307_az_nycschools.view.ISchoolDetailsView;
import com.zykov.andrii.a201801307_az_nycschools.view.SchoolDetailsFragment;

/**
 * Created by andrii on 3/7/18.
 */

public class SchoolDetailsPresenterImpl implements SchoolDetailsFragment.ISchoolDetailsPresenter {

    private ISchoolDetailsView view;

    public SchoolDetailsPresenterImpl() {

    }

    @Override
    public void bindView(ISchoolDetailsView view) {
        this.view = view;
    }

    @Override
    public void showSchoolDetails(SchoolWrapper schoolWrapper) {
        view.showSchoolName(schoolWrapper.getSchoolName());
        if (schoolWrapper.getSchoolSatWrapper() != null) {
            view.showSchoolSatAvgMath(schoolWrapper.getSchoolSatWrapper().getMathAvgScore());
            view.showSchoolSatAvgReading(schoolWrapper.getSchoolSatWrapper().getReadingAvgScore());
            view.showSchoolSatAvgWriting(schoolWrapper.getSchoolSatWrapper().getWritingAvgScore());
        } else {
            view.showSatInfoNotAvailable();
        }
        view.showAddressLine1(schoolWrapper.getAddress1());
        view.showCity(schoolWrapper.getCity());
        view.showStateCode(schoolWrapper.getStateCode());
        view.showZip(schoolWrapper.getZip());

    }
}
