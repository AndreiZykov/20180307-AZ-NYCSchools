package com.zykov.andrii.a201801307_az_nycschools.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.zykov.andrii.a201801307_az_nycschools.R;
import com.zykov.andrii.a201801307_az_nycschools.SchoolsApplication;
import com.zykov.andrii.a201801307_az_nycschools.data.SchoolWrapper;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andrii on 3/7/18.
 */

public class SchoolDetailsFragment extends Fragment implements ISchoolDetailsView {

    public static String TAG = SchoolDetailsFragment.class.getSimpleName();

    public static String SCHOOL_WRAPPER_ARGS_KEY = TAG + ".SCHOOL_WRAPPER_ARGS_KEY";

    @Inject
    ISchoolDetailsPresenter presenter;

    @BindView(R.id.fragment_school_details_sat_reading_value_text_view)
    TextView avgReadingValueTextView;

    @BindView(R.id.fragment_school_details_sat_writing_value_text_view)
    TextView avgWritingValueTextView;

    @BindView(R.id.fragment_school_details_sat_math_value_text_view)
    TextView avgMathValueTextView;

    @BindView(R.id.fragment_school_details_school_name_text_view)
    TextView schoolNameTextView;

    @BindView(R.id.fragment_school_details_school_address_line_1_text_view)
    TextView addressList1TextView;

    @BindView(R.id.fragment_school_details_school_address_city_text_view)
    TextView addressCityTextView;

    @BindView(R.id.fragment_school_details_school_address_state_text_view)
    TextView addressStateCodeTextView;

    @BindView(R.id.fragment_school_details_school_address_zip_text_view)
    TextView addressZipTextView;

    public interface ISchoolDetailsPresenter {
        void bindView(ISchoolDetailsView view);

        void showSchoolDetails(SchoolWrapper schoolWrapper);
    }

    public static SchoolDetailsFragment getInstance(SchoolWrapper schoolWrapper) {
        SchoolDetailsFragment fragment = new SchoolDetailsFragment();
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<SchoolWrapper> jsonAdapter = moshi.adapter(SchoolWrapper.class);
        Bundle args = new Bundle();
        args.putString(SCHOOL_WRAPPER_ARGS_KEY, jsonAdapter.toJson(schoolWrapper));
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_school_details_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        SchoolWrapper schoolWrapper = null;
        if (getArguments() != null && getArguments().containsKey(SCHOOL_WRAPPER_ARGS_KEY)) {
            String schoolWrapperString = (String) getArguments().get(SCHOOL_WRAPPER_ARGS_KEY);
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<SchoolWrapper> jsonAdapter = moshi.adapter(SchoolWrapper.class);
            try {
                schoolWrapper = jsonAdapter.fromJson(schoolWrapperString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SchoolsApplication.appComponent.inject(this);
        presenter.bindView(this);
        presenter.showSchoolDetails(schoolWrapper);
        return view;
    }


    @Override
    public void showSchoolName(String schoolName) {
        schoolNameTextView.setText(schoolName);
    }

    @Override
    public void showSatInfoNotAvailable() {

    }

    @Override
    public void showSchoolSatAvgMath(String mathAvgScore) {
        avgMathValueTextView.setText(mathAvgScore);
    }

    @Override
    public void showSchoolSatAvgReading(String readingAvgScore) {
        avgReadingValueTextView.setText(readingAvgScore);
    }

    @Override
    public void showSchoolSatAvgWriting(String writingAvgScore) {
        avgWritingValueTextView.setText(writingAvgScore);
    }

    @Override
    public void showAddressLine1(String address1) {
        addressList1TextView.setText(address1);
    }

    @Override
    public void showCity(String city) {
        addressCityTextView.setText(city);
    }

    @Override
    public void showStateCode(String stateCode) {
        addressStateCodeTextView.setText(stateCode);
    }

    @Override
    public void showZip(String zip) {
        addressZipTextView.setText(zip);
    }

}
