package com.zykov.andrii.a201801307_az_nycschools.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zykov.andrii.a201801307_az_nycschools.R;
import com.zykov.andrii.a201801307_az_nycschools.data.SchoolWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andrii on 3/7/18.
 */

public class SchoolsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String filterString = "";

    private final List<SchoolWrapper> data = new ArrayList<>();
    private final List<SchoolWrapper> filteredData = new ArrayList<>();


    private SchoolsAdapterListener listener = DEFAULT_LISTENER;

    public SchoolsAdapter(SchoolsAdapterListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SchoolViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.school_item_view_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SchoolViewHolder) holder).bindView(filteredData.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    public void applyFilterSting(String searchString) {
        this.filterString = searchString;
        filteredData.clear();
        for(SchoolWrapper sw: data){
            if(filterString == null ||
                    filterString.equalsIgnoreCase("") ||
                    contains(sw.getSchoolName(), filterString))
                filteredData.add(sw);
        }
    }

    private boolean contains(String schoolName, String filterString) {
        if(schoolName == null)
            return false;
        return schoolName.toLowerCase().contains(filterString.toLowerCase());
    }

    public void setData(List<SchoolWrapper> data) {
        this.data.clear();
        this.data.addAll(data);
        applyFilterSting(filterString);
    }

    class SchoolViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.school_item_view_layout_school_name_text_view)
        TextView schoolNameTextView;

        public SchoolViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> listener.onItemSelected(filteredData.get(getAdapterPosition())));
        }

        public void bindView(SchoolWrapper schoolWrapper) {
            schoolNameTextView.setText(schoolWrapper.getSchoolName());
        }

    }


    public interface SchoolsAdapterListener {
        void onItemSelected(SchoolWrapper schoolWrapper);
    }

    private static SchoolsAdapterListener DEFAULT_LISTENER = schoolWrapper -> {

    };

}
