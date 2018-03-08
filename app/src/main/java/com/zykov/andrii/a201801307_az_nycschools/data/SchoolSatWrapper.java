package com.zykov.andrii.a201801307_az_nycschools.data;

import com.squareup.moshi.Json;

/**
 * Created by andrii on 3/7/18.
 */

public class SchoolSatWrapper {

    @Json(name = "dbn")
    String dbn;

    @Json(name = "school_name")
    String schoolName;

    @Json(name = "num_of_sat_test_takers")
    String numSatTestTakers;

    @Json(name = "sat_critical_reading_avg_score")
    String readingAvgScore;

    @Json(name = "sat_math_avg_score")
    String mathAvgScore;

    @Json(name = "sat_writing_avg_score")
    String writingAvgScore;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getNumSatTestTakers() {
        return numSatTestTakers;
    }

    public void setNumSatTestTakers(String numSatTestTakers) {
        this.numSatTestTakers = numSatTestTakers;
    }

    public String getReadingAvgScore() {
        return readingAvgScore;
    }

    public void setReadingAvgScore(String readingAvgScore) {
        this.readingAvgScore = readingAvgScore;
    }

    public String getMathAvgScore() {
        return mathAvgScore;
    }

    public void setMathAvgScore(String mathAvgScore) {
        this.mathAvgScore = mathAvgScore;
    }

    public String getWritingAvgScore() {
        return writingAvgScore;
    }

    public void setWritingAvgScore(String writingAvgScore) {
        this.writingAvgScore = writingAvgScore;
    }

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

}
