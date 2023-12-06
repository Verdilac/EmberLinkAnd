package com.example.emberlinkand.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventDao {

    @Query("SELECT * FROM  event")
    LiveData<List<Event>> getAllEvents();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertEvent(Event... event);

    @Query("DELETE FROM event WHERE uid = :uid")
    void delete(int uid);

    @Query("SELECT * FROM event WHERE uid=:id")
    LiveData<Event> findByEventID(int id);
}


