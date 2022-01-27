package com.example.finalproject_joshromney;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproject_joshromney.db.Activity;
import com.example.finalproject_joshromney.db.AppDatabase;
import com.example.finalproject_joshromney.db.SavedActivity;

import java.util.List;

public class SavedActivityViewModel extends ViewModel {
    private LiveData<List<SavedActivity>> savedActivityList;

    public LiveData<List<SavedActivity>> getSavedActivityList(Context c)
    {
        if (savedActivityList != null)
        {
            return savedActivityList;
        }
        else
        {
            return savedActivityList = AppDatabase.getInstance(c).savedActivityDAO().getAll();
        }
    }
}
