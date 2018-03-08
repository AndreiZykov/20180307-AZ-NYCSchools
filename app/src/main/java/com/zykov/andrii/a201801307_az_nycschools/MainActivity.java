package com.zykov.andrii.a201801307_az_nycschools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.zykov.andrii.a201801307_az_nycschools.data.SchoolWrapper;
import com.zykov.andrii.a201801307_az_nycschools.event.SchoolSearchEvent;
import com.zykov.andrii.a201801307_az_nycschools.view.SchoolDetailsFragment;
import com.zykov.andrii.a201801307_az_nycschools.view.SchoolsFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SchoolsFragment.SchoolFragmentListener, SearchView.OnQueryTextListener {

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private SearchView searchView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.nyc_schools);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> MainActivity.this.onBackPressed());
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.fragment_container,
                        SchoolsFragment.getInstance(),
                        SchoolsFragment.TAG)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() >= 1) {
            Fragment f = getSupportFragmentManager().findFragmentByTag(SchoolDetailsFragment.TAG);
            if (f instanceof SchoolDetailsFragment) {
                getSupportFragmentManager().popBackStackImmediate();
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
                searchView.setVisibility(View.VISIBLE);
                return;
            }
        }
        finish();
    }

    @Override
    public void showSchoolDetails(SchoolWrapper schoolWrapper) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        searchView.setVisibility(View.GONE);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.fragment_container,
                        SchoolDetailsFragment.getInstance(schoolWrapper),
                        SchoolDetailsFragment.TAG)
                .addToBackStack(SchoolDetailsFragment.TAG)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        EventBus.getDefault().post(new SchoolSearchEvent(query));
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

}
