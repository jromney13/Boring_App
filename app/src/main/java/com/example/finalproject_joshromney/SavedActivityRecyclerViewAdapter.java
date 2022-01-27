package com.example.finalproject_joshromney;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_joshromney.db.Activity;
import com.example.finalproject_joshromney.db.SavedActivity;

import java.util.List;

public class SavedActivityRecyclerViewAdapter extends RecyclerView.Adapter<SavedActivityRecyclerViewAdapter.ViewHolder> {

    public final List<SavedActivity> savedActivities;

    public SavedActivityRecyclerViewAdapter(List<SavedActivity> savedActivities) {
        this.savedActivities = savedActivities;
    }

    public void addItems(List<SavedActivity> savedActivities) {
        this.savedActivities.clear();
        this.savedActivities.addAll(savedActivities);
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
    public SavedActivityRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedActivityRecyclerViewAdapter.ViewHolder holder, int position) {
        final SavedActivity savedActivity = savedActivities.get(position);
        if(savedActivity != null)
        {
            holder.txtActivityName.setText(savedActivity.getActivity());

            if(savedActivity.isCompleted())
            holder.itemView.setBackgroundColor(Color.parseColor("#567845"));

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("savedActivity_pk", savedActivity.getKey());

                    ListActivityDetailsFragment listActivityDetails = new ListActivityDetailsFragment();
                    listActivityDetails.setArguments(bundle);

                    AppCompatActivity appCompatActivity = (AppCompatActivity)v.getContext();
                    appCompatActivity.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, listActivityDetails)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return savedActivities.size();
    }
}
