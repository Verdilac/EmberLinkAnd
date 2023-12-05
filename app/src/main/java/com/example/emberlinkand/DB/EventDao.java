package com.example.emberlinkand.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventDao {

    @Query("SELECT * FROM  event")
    LiveData<List<Event>> getAllEvents();

    @Insert
    void insertEvent(Event... event);

    @Delete
    void delete(Event event);


}


