package com.ashish.asiaexplorer.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Country.class}, version = 1)
public abstract class CountryDatabase extends RoomDatabase {

    private static CountryDatabase instance;
    public abstract CountryDao countryDao();
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized CountryDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),CountryDatabase.class,"country_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

}
