package com.example.finalproject_joshromney;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalproject_joshromney.db.Activity;
import com.example.finalproject_joshromney.db.AppDatabase;
import com.example.finalproject_joshromney.db.SavedActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class AddActivityDetails extends Fragment {

    View view;

    private TextView txtActivityTitle, txtParticipantTitle, txtPrice, txtWebLink;
    private int activity_pk;
    Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_activity_details, container, false);

        txtActivityTitle = view.findViewById(R.id.activityTitle);
        txtParticipantTitle = view.findViewById(R.id.participantNumber);
        txtPrice = view.findViewById(R.id.price);
        txtWebLink = view.findViewById(R.id.webLink);

        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            activity_pk = bundle.getInt("activity_pk");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    activity = AppDatabase.getInstance(getContext())
                            .activityDAO()
                            .getByKey(activity_pk);
                    txtActivityTitle.setText(activity.getActivity());
                    txtParticipantTitle.setText(activity.getParticipants());

                    float price = Float.parseFloat(activity.getPrice());
                    if(price == 0){
                        txtPrice.setText("Free");
                    }
                    else if (price > 0 && price <.21) {
                        txtPrice.setText("$");
                    }
                    else if (price >.21 && price <.41) {
                        txtPrice.setText("$$");
                    }
                    else
                    {
                        txtPrice.setText("$$$");
                    }

                    txtWebLink.setText(activity.getLink());
                }
            }).start();
        }



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId())
                {
                    case R.id.btnAddActivity:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                activity = AppDatabase.getInstance(getContext())
                                        .activityDAO()
                                        .getByKey(activity_pk);

                                SavedActivity savedActivity = new SavedActivity(activity.getActivity(),
                                        activity.getParticipants(), activity.getPrice(), activity.getLink(), false, activity.getKey());

                                AppDatabase.getInstance(getContext())
                                        .savedActivityDAO()
                                        .insert(savedActivity);

                                AppDatabase.getInstance(getContext())
                                        .activityDAO()
                                        .delete(activity);

                                List<SavedActivity> savedActivityList = AppDatabase.getInstance(getContext())
                                        .savedActivityDAO()
                                        .getAllList();

                                for (SavedActivity a: savedActivityList)
                                {
                                    Log.d("SavedActivityList", "SavedActivityList: " + a.toString());
                                }

                            }
                        }).start();

                        ListTaskFragment listTaskFragment = new ListTaskFragment();

                        AppCompatActivity activity = (AppCompatActivity)view.getContext();
                        activity.getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, listTaskFragment)
                                .addToBackStack(null)
                                .commit();
                        break;
                }

            }
        };
        view.findViewById(R.id.btnAddActivity).setOnClickListener(btnListener);

    }


}