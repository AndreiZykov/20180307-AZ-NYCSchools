package com.zykov.andrii.a201801307_az_nycschools.utils.nycschoolservice;

import com.zykov.andrii.a201801307_az_nycschools.data.SchoolSatWrapper;
import com.zykov.andrii.a201801307_az_nycschools.data.SchoolWrapper;


import io.reactivex.Observable;
import java.util.List;

import retrofit2.http.GET;

/**
 * Created by andrii on 3/7/18.
 */

public interface NYCSchoolsAPI {
    @GET("97mf-9njv.json")
    Observable<List<SchoolWrapper>> getSchools();
    @GET("734v-jeq5.json")
    Observable<List<SchoolSatWrapper>> getSchoolsSat();
}
