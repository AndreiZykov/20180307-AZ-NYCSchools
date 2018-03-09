package com.zykov.andrii.a201801307_az_nycschools.presenter;

import android.content.Context;

import com.zykov.andrii.a201801307_az_nycschools.R;
import com.zykov.andrii.a201801307_az_nycschools.data.SchoolSatWrapper;
import com.zykov.andrii.a201801307_az_nycschools.data.SchoolWrapper;
import com.zykov.andrii.a201801307_az_nycschools.utils.nycschoolservice.NYCSchoolsAPI;
import com.zykov.andrii.a201801307_az_nycschools.view.ISchoolView;
import com.zykov.andrii.a201801307_az_nycschools.view.SchoolsFragment;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by andrii on 3/7/18.
 */

public class SchoolsPresenterImpl implements SchoolsFragment.ISchoolsFragmentPresenter {

    private ISchoolView view;

    private final NYCSchoolsAPI nycSchoolsAPI;

    private final Context context;

    HashMap<String, SchoolSatWrapper> schoolSatHash = null;

    public SchoolsPresenterImpl(NYCSchoolsAPI nycSchoolsAPI, Context context) {
        this.nycSchoolsAPI = nycSchoolsAPI;
        this.context = context;
    }

    @Override
    public void bindView(ISchoolView view) {
        this.view = view;
    }

    @Override
    public void loadSchools() {
        view.showProgressBar();
        nycSchoolsAPI.getSchools().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doFinally(() -> view.hideProgressBar()).
                subscribe(schoolWrappers -> view.showSchools(schoolWrappers), th -> {
                    view.showError(context.getString(R.string.load_schools_error));
                    view.showReloadButton();
                });
    }

    @Override
    public void onSchoolItemSelected(SchoolWrapper schoolWrapper) {
        onSchoolItemSelectedInt(schoolWrapper, true);
    }

    private void onSchoolItemSelectedInt(SchoolWrapper schoolWrapper, boolean refresh) {
        if (schoolSatHash == null) {
            if (refresh) {
                view.showProgressBar();
                Observable<List<SchoolSatWrapper>> observSat = nycSchoolsAPI.getSchoolsSat();
                observSat.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doFinally(() -> view.hideProgressBar()).map(schoolSatWrappers -> {
                    HashMap<String, SchoolSatWrapper> h = new HashMap<>();
                    for (SchoolSatWrapper s : schoolSatWrappers)
                        h.put(s.getDbn(), s);
                    return h;
                }).subscribe(res -> {
                    schoolSatHash = res;
                    onSchoolItemSelectedInt(schoolWrapper, false);
                }, th -> {
                    view.showError(context.getString(R.string.load_sat_error));
                });
            } else {
                view.showSchoolDetails(schoolWrapper);
            }
        } else {
            SchoolSatWrapper satWrapper = null;
            satWrapper = schoolSatHash.get(schoolWrapper.getDbn());
            schoolWrapper.setSatResults(satWrapper);
            view.showSchoolDetails(schoolWrapper);
        }
    }

}
