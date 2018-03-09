package com.zykov.andrii.a201801307_az_nycschools.view;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zykov.andrii.a201801307_az_nycschools.R;
import com.zykov.andrii.a201801307_az_nycschools.SchoolsApplication;
import com.zykov.andrii.a201801307_az_nycschools.data.SchoolWrapper;
import com.zykov.andrii.a201801307_az_nycschools.event.SchoolSearchEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by andrii on 3/7/18.
 */

public class SchoolsFragment extends Fragment implements ISchoolView, SchoolsAdapter.SchoolsAdapterListener {

    public static String TAG = SchoolsFragment.class.getSimpleName();

    public interface ISchoolsFragmentPresenter {
        void bindView(ISchoolView view);
        void loadSchools();
        void onSchoolItemSelected(SchoolWrapper schoolWrapper);
    }

    private final Handler handler = new Handler();

    @Inject
    ISchoolsFragmentPresenter presenter;

    @BindView(R.id.school_fragment_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.schools_fragment_recycler_view)
    RecyclerView schoolsRecyclerView;

    @BindView(R.id.school_fragment_reload_button)
    Button reloadButton;

    public static SchoolsFragment getInstance() {
        return new SchoolsFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_school_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        schoolsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        schoolsRecyclerView.setAdapter(new SchoolsAdapter(this));
        SchoolsApplication.appComponent.inject(this);
        presenter.bindView(this);
        presenter.loadSchools();
        return view;
    }

    @Override
    public void showSchools(List<SchoolWrapper> schoolWrappers) {
        ((SchoolsAdapter) schoolsRecyclerView.getAdapter()).setData(schoolWrappers);
        schoolsRecyclerView.getAdapter().notifyDataSetChanged();
        schoolsRecyclerView.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.school_fragment_reload_button})
    public void onReloadChoolsClicked(){
        reloadButton.setVisibility(View.GONE);
        presenter.loadSchools();
    }

    @Override
    public void showError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSchoolDetails(SchoolWrapper schoolWrapper) {
        if(getActivity() != null && getActivity() instanceof SchoolFragmentListener)
            ((SchoolFragmentListener) getActivity()).showSchoolDetails(schoolWrapper);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showReloadButton() {
        reloadButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onItemSelected(SchoolWrapper schoolWrapper) {
        presenter.onSchoolItemSelected(schoolWrapper);
    }

    public interface SchoolFragmentListener {
        void showSchoolDetails(SchoolWrapper schoolWrapper);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSearchQueryEvent(SchoolSearchEvent event){
        ((SchoolsAdapter) schoolsRecyclerView.getAdapter()).applyFilterSting(event.getSearchString());
        handler.post(() -> schoolsRecyclerView.getAdapter().notifyDataSetChanged());
    }

}
