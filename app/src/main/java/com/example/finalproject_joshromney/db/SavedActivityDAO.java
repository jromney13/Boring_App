package com.example.finalproject_joshromney.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SavedActivityDAO {

    @Query("select * from SavedActivity")
    LiveData<List<SavedActivity>> getAll();

    @Query("DELETE FROM SavedActivity")
    void delete();

    @Query("select * from SavedActivity where `key` =:key")
    SavedActivity getByKey(int key);

    @Query("select * from SavedActivity")
    List<SavedActivity> getAllList();

    @Query("UPDATE SavedActivity set completed = :status where `key` =:key")
    int updateStatus(int key, Boolean status);

    @Delete
    void delete(SavedActivity savedActivity);

    @Insert
    void insert(SavedActivity... savedActivities);
}
