package com.example.emberlinkand.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Event.class},version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public  abstract EventDao eventDao();

    private static AppDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"EventsDatabase").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return  INSTANCE;
    }
}
