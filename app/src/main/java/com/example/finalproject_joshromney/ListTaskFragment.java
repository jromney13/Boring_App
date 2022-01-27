package com.example.finalproject_joshromney;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject_joshromney.db.SavedActivity;

import java.util.ArrayList;
import java.util.List;


public class ListTaskFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private SavedActivityRecyclerViewAdapter savedActivityRecyclerViewAdapter;
    private int columnCount = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_list_task, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewSaved);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Context context = getContext();
        savedActivityRecyclerViewAdapter = new SavedActivityRecyclerViewAdapter(new ArrayList<SavedActivity>());

        if(columnCount <= 1)
        {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
        else
        {
            recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
        }

        recyclerView.setAdapter(savedActivityRecyclerViewAdapter);
        recyclerView.setHasFixedSize(false);

        ViewModelProviders.of(this)
                .get(SavedActivityViewModel.class)
                .getSavedActivityList(context)
                .observe(this, new Observer<List<SavedActivity>>() {
                    @Override
                    public void onChanged(List<SavedActivity> savedActivities) {
                        if(savedActivities != null)
                        {
                            savedActivityRecyclerViewAdapter.addItems(savedActivities);
                        }
                    }
                });
    }
}