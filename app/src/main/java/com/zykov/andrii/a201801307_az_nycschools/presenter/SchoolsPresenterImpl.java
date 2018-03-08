package com.zykov.andrii.a201801307_az_nycschools.presenter;

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

    HashMap<String, SchoolSatWrapper> schoolSatHash = null;

    public SchoolsPresenterImpl(NYCSchoolsAPI nycSchoolsAPI) {
        this.nycSchoolsAPI = nycSchoolsAPI;
    }

    @Override
    public void bindView(ISchoolView view) {
        this.view = view;
    }

    @Override
    public void loadSchools() {
        view.showProgressBar();
        Observable<List<SchoolWrapper>> observSchools = nycSchoolsAPI.getSchools();
        observSchools.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> view.hideProgressBar())
                .subscribe(schoolWrappers -> view.showSchools(schoolWrappers), th -> {
                    th.printStackTrace();
                    view.showError("Can not load schools");
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
                observSat.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(() -> view.hideProgressBar())
                        .map(schoolSatWrappers -> {
                            HashMap<String, SchoolSatWrapper> h = new HashMap<>();
                            for (SchoolSatWrapper s : schoolSatWrappers)
                                h.put(s.getDbn(), s);
                            return h;
                        })
                        .subscribe(res -> {
                            schoolSatHash = res;
                            onSchoolItemSelectedInt(schoolWrapper, false);
                        }, th -> {
                            th.printStackTrace();
                            view.showError("Error loading SATs");
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
