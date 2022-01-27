package com.example.finalproject_joshromney;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.finalproject_joshromney.db.Activity;

import java.util.ArrayList;
import java.util.List;

public class AddTaskFragment extends Fragment {

    private OnGenerateTasksClick listener;

    interface OnGenerateTasksClick
    {
        void generateTasksClick();
    }

    View view;
    private RecyclerView recyclerView;
    private ActivityRecyclerViewAdapter activityRecyclerViewAdapter;
    private int columnCount = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_task, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewAdd);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof AddTaskFragment.OnGenerateTasksClick){
            listener = (AddTaskFragment.OnGenerateTasksClick)context;
        }
        else
        {
            throw new ClassCastException(context.toString() + "Must Implement Listener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Context context = getContext();
        activityRecyclerViewAdapter = new ActivityRecyclerViewAdapter(new ArrayList<Activity>());

        if(columnCount <= 1)
        {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
        else
        {
            recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
        }

        recyclerView.setAdapter(activityRecyclerViewAdapter);
        recyclerView.setHasFixedSize(false);

        ViewModelProviders.of(this)
                .get(AllActivityViewModel.class)
                .getActivityList(context)
                .observe(this, new Observer<List<Activity>>() {
                    @Override
                    public void onChanged(List<Activity> activities) {
                        if(activities != null)
                        {
                            activityRecyclerViewAdapter.addItems(activities);
                        }
                    }
                });

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId())
                {
                    case R.id.btnNewActivities:
                        listener.generateTasksClick();
                        break;
                }
            }
        };
        view.findViewById(R.id.btnNewActivities).setOnClickListener(btnListener);
    }

    public void displayActivity(Activity activity)
    {
        Log.d("AddTaskFragment", "displayActivity: ");
    }
}