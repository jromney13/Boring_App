package com.example.finalproject_joshromney;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproject_joshromney.db.Activity;
import com.example.finalproject_joshromney.db.AppDatabase;

import java.util.List;

public class AllActivityViewModel extends ViewModel {
    private LiveData<List<Activity>> activityList;

    public LiveData<List<Activity>> getActivityList(Context c)
    {
        if (activityList != null)
        {
            return activityList;
        }
        else
        {
            return activityList = AppDatabase.getInstance(c).activityDAO().getAll();
        }
    }
}
