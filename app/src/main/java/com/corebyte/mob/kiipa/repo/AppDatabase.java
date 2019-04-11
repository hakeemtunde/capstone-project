package com.corebyte.mob.kiipa.repo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.corebyte.mob.kiipa.model.Category;

@Database(entities = {Category.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "kiipa-db";

    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (new Object()) {
                Log.i(AppDatabase.class.getSimpleName(), "Creating db connection");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, DB_NAME)
                        .build();
            }
        }
        return sInstance;
    }

    public abstract CategoryDao categoryDao();
}
