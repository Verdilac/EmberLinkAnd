package com.example.emberlinkand.DB;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class EventRepository {

    private EventDao eventDao;
    private LiveData<List<Event>> allEvents;

    EventRepository(Application application) {
        AppDatabase db = AppDatabase.getDbInstance(application);
        eventDao = db.eventDao();
        allEvents = eventDao.getAllEvents();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }
}
