package com.example.emberlinkand.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Event.class},version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public  abstract EventDao eventDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"EventsDatabase").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return  INSTANCE;
    }
}
