package com.zykov.andrii.a201801307_az_nycschools.data;

import com.squareup.moshi.Json;

/**
 * Created by andrii on 3/7/18.
 */

public class SchoolWrapper {

    @Json(name = "school_name")
    private String schoolName;

    @Json(name = "dbn")
    private String dbn;

    @Json(name = "boro")
    private String boro;

    @Json(name = "phone_number")
    private String phoneNumber;

    @Json(name = "primary_address_line_1")
    private String address1;

    @Json(name = "city")
    private String city;

    @Json(name = "zip")
    private String zip;

    @Json(name = "state_code")
    private String stateCode;

    @Json(name = "latitude")
    private Double lat;

    @Json(name = "longitude")
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    private SchoolSatWrapper schoolSatWrapper;

    public String getDbn() {
        return dbn;
    }

    public String getBoro() {
        return boro;
    }

    public void setBoro(String boro) {
        this.boro = boro;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public SchoolSatWrapper getSchoolSatWrapper() {
        return schoolSatWrapper;
    }

    public void setSchoolSatWrapper(SchoolSatWrapper schoolSatWrapper) {
        this.schoolSatWrapper = schoolSatWrapper;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setSatResults(SchoolSatWrapper schoolSatWrapper) {
        this.schoolSatWrapper = schoolSatWrapper;
    }
}
