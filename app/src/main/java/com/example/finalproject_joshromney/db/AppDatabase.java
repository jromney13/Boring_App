package com.example.finalproject_joshromney.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Activity.class, SavedActivity.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context)
    {
        if(instance != null)
        {
            return instance;
        }

        else
        {
            instance = Room.databaseBuilder(context, AppDatabase.class, "user-database")
                    .build();

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "App.db")
                    .fallbackToDestructiveMigration()
                    .build();

            return instance;
        }
    }
    public abstract ActivityDAO activityDAO();
    public abstract SavedActivityDAO savedActivityDAO();
}
