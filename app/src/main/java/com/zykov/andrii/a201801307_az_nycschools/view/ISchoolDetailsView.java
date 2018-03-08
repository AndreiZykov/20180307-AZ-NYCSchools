package com.zykov.andrii.a201801307_az_nycschools.view;

/**
 * Created by andrii on 3/7/18.
 */

public interface ISchoolDetailsView {
    void showSchoolName(String schoolName);
    void showSchoolSatAvgMath(String mathAvgScore);
    void showSatInfoNotAvailable();
    void showSchoolSatAvgReading(String readingAvgScore);
    void showSchoolSatAvgWriting(String writingAvgScore);
    void showAddressLine1(String address1);
    void showCity(String city);
    void showStateCode(String stateCode);
    void showZip(String zip);
}
