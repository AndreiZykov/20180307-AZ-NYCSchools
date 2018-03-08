package com.zykov.andrii.a201801307_az_nycschools.event;

/**
 * Created by andrii on 3/8/18.
 */

public class SchoolSearchEvent {

    private final String searchString;

    public SchoolSearchEvent(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }

}
