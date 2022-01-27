package com.example.finalproject_joshromney;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.finalproject_joshromney.db.Activity;
import com.example.finalproject_joshromney.db.AppDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddTaskFragment.OnGenerateTasksClick {

    Toolbar toolbar;
    private GetActivities task;
    private GetActivities task2;
    private GetActivities task3;
    private GetActivities task4;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getSupportFragmentManager();

        fm.beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment(), "Home")
                .commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;

                        switch (item.getItemId())
                        {
                            case (R.id.nav_home):
                                selectedFragment = new HomeFragment();
                                break;
                            case (R.id.nav_add):
                                selectedFragment = new AddTaskFragment();
                                break;
                            case (R.id.nav_list):
                                selectedFragment = new ListTaskFragment();
                                break;
                        }

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, selectedFragment)
                                .commit();
                        return true;
                    }
                };

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        generateTasks();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.tool_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(getBaseContext())
                        .savedActivityDAO()
                        .delete();
            }
        }).start();
        return true;
    }

    public void generateTasks()
    {task = new GetActivities();
        task.setOnActivityListImportListener(new GetActivities.OnActivityListImport() {
            @Override
            public void completedActivityList(Activity activities) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase.getInstance(getBaseContext()).activityDAO().delete();

                        AppDatabase.getInstance(getBaseContext())
                                .activityDAO()
                                .insert(activities);

                        List<Activity> activityList = AppDatabase.getInstance(getBaseContext())
                                .activityDAO()
                                .getAllList();

                    }
                }).start();
            }
        });
        task.execute("");

        task2 = new GetActivities();
        task2.setOnActivityListImportListener(new GetActivities.OnActivityListImport() {
            @Override
            public void completedActivityList(Activity activities) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        AppDatabase.getInstance(getBaseContext())
                                .activityDAO()
                                .insert(activities);

                        List<Activity> activityList = AppDatabase.getInstance(getBaseContext())
                                .activityDAO()
                                .getAllList();

                    }
                }).start();
            }
        });
        task2.execute("");

        task3 = new GetActivities();
        task3.setOnActivityListImportListener(new GetActivities.OnActivityListImport() {
            @Override
            public void completedActivityList(Activity activities) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        AppDatabase.getInstance(getBaseContext())
                                .activityDAO()
                                .insert(activities);

                        List<Activity> activityList = AppDatabase.getInstance(getBaseContext())
                                .activityDAO()
                                .getAllList();

                    }
                }).start();
            }
        });
        task3.execute("");

        task4 = new GetActivities();
        task4.setOnActivityListImportListener(new GetActivities.OnActivityListImport() {
            @Override
            public void completedActivityList(Activity activities) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        AppDatabase.getInstance(getBaseContext())
                                .activityDAO()
                                .insert(activities);

                        List<Activity> activityList = AppDatabase.getInstance(getBaseContext())
                                .activityDAO()
                                .getAllList();

                        for (Activity a: activityList)
                        {
                            Log.d("ActivityList", "ActivityList: " + a.toString());
                        }
                    }
                }).start();
            }
        });
        task4.execute("");

    }

    @Override
    public void generateTasksClick() {
        generateTasks();
    }
}