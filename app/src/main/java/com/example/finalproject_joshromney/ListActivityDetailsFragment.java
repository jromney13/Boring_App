package com.example.finalproject_joshromney;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalproject_joshromney.db.Activity;
import com.example.finalproject_joshromney.db.AppDatabase;
import com.example.finalproject_joshromney.db.SavedActivity;


public class ListActivityDetailsFragment extends Fragment {

    View view;

    private TextView txtActivityTitle, txtParticipantTitle, txtPrice, txtWebLink, txtCompleted;
    private int savedActivity_pk;
    SavedActivity savedActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_list_activity_details, container, false);

        txtActivityTitle = view.findViewById(R.id.activityTitle);
        txtParticipantTitle = view.findViewById(R.id.participantNumber);
        txtPrice = view.findViewById(R.id.price);
        txtWebLink = view.findViewById(R.id.webLink);
        txtCompleted = view.findViewById(R.id.completedStatus);


        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            savedActivity_pk = bundle.getInt("savedActivity_pk");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    savedActivity = AppDatabase.getInstance(getContext())
                            .savedActivityDAO()
                            .getByKey(savedActivity_pk);

                    txtActivityTitle.setText(savedActivity.getActivity());
                    txtParticipantTitle.setText(savedActivity.getParticipants());

                    float price = Float.parseFloat(savedActivity.getPrice());
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

                    txtWebLink.setText(savedActivity.getLink());

                    if(savedActivity.isCompleted()) {
                        txtCompleted.setText("Completed");
                    }
                    else {
                        txtCompleted.setText("Not Completed");
                    }


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
                    case R.id.btnCompleteActivity:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                savedActivity = AppDatabase.getInstance(getContext())
                                        .savedActivityDAO()
                                        .getByKey(savedActivity_pk);

                                AppDatabase.getInstance(getContext())
                                        .savedActivityDAO()
                                        .updateStatus(savedActivity_pk, true);


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

                    case R.id.btnDeleteActivity:

                        boolean deleteConfirm = false;

                        new AlertDialog.Builder(getContext())
                                .setTitle("Delete Confirmation")
                                .setMessage("Do you really want to delete?")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                savedActivity = AppDatabase.getInstance(getContext())
                                                        .savedActivityDAO()
                                                        .getByKey(savedActivity_pk);

                                                AppDatabase.getInstance(getContext())
                                                        .savedActivityDAO()
                                                        .delete(savedActivity);
                                            }
                                        }).start();

                                        ListTaskFragment listTaskFragment = new ListTaskFragment();

                                        AppCompatActivity activity = (AppCompatActivity)view.getContext();
                                        activity.getSupportFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.fragment_container, listTaskFragment)
                                                .addToBackStack(null)
                                                .commit();
                                    }
                                })
                                .setNegativeButton("No", null).show();
                        break;
                }


            }
        };
        view.findViewById(R.id.btnCompleteActivity).setOnClickListener(btnListener);
        view.findViewById(R.id.btnDeleteActivity).setOnClickListener(btnListener);

    }
}