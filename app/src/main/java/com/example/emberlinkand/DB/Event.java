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
    @ColumnInfo(name = "participant_limit")
    public String participantLimit;
    @ColumnInfo(name = "event_time")
    public String eventTime;
    @ColumnInfo(name = "event_venue")
    public String eventvenue;

    @ColumnInfo(name = "event_description")
    public String eventDescription;

    @ColumnInfo(name = "event_tag")
    public String eventtag;


}
