package com.example.finalproject_joshromney.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ActivityDAO {

    @Query("select * from Activity")
    LiveData<List<Activity>> getAll();

    @Query("DELETE FROM Activity")
    void delete();

    @Query("select * from Activity where `key` =:key")
    Activity getByKey(int key);

    @Query("select * from Activity")
    List<Activity> getAllList();

    @Delete
    void delete(Activity activity);

    @Insert
    void insert(Activity... activities);

}
