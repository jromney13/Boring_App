package com.example.finalproject_joshromney;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_joshromney.db.Activity;

import java.util.List;

public class ActivityRecyclerViewAdapter extends RecyclerView.Adapter<ActivityRecyclerViewAdapter.ViewHolder>{

    public final List<Activity> activities;

    public ActivityRecyclerViewAdapter(List<Activity> activities) {
        this.activities = activities;
    }

    public void addItems(List<Activity> activities) {
        this.activities.clear();
        this.activities.addAll(activities);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public View view;
        public Activity activity;
        public TextView txtActivityName;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            view = itemView;
            txtActivityName = view.findViewById(R.id.riTextActivityName);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Activity activity = activities.get(position);
        if(activity != null)
        {
            holder.txtActivityName.setText(activity.getActivity());

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("activity_pk", activity.getKey());

                    AddActivityDetails addActivityDetails = new AddActivityDetails();
                    addActivityDetails.setArguments(bundle);

                    AppCompatActivity appCompatActivity = (AppCompatActivity)v.getContext();
                    appCompatActivity.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, addActivityDetails)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }
}
