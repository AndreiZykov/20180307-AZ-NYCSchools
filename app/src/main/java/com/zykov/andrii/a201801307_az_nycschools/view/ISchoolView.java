package com.zykov.andrii.a201801307_az_nycschools.view;

import com.zykov.andrii.a201801307_az_nycschools.data.SchoolWrapper;

import java.util.List;

/**
 * Created by andrii on 3/7/18.
 */

public interface ISchoolView {
    void showSchools(List<SchoolWrapper> schoolWrappers);
    void showError(String s);
    void showSchoolDetails(SchoolWrapper schoolWrapper);
    void showProgressBar();
    void hideProgressBar();
    void showReloadButton();
}
