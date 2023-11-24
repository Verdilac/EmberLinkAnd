package com.example.emberlinkand.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Event {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "organizer_name")
    public String organizerName;


    @ColumnInfo(name = "event_name")
    public String eventName;

}
