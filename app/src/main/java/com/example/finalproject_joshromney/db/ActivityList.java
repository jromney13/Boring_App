package com.example.finalproject_joshromney.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ActivityList {

    @Override
    public String toString() {
        return "ActivityList{" +
                "activityList=" + activityList +
                '}';
    }

    @SerializedName("activity")
    private ArrayList<Activity> activityList;

    public ArrayList<Activity> getActivityList() {
        return activityList;
    }
}
