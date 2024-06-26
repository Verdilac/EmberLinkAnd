package com.example.emberlinkand.DB;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class EventRepository {

    private EventDao eventDao;
    private LiveData<List<Event>> allEvents;
    private LiveData<Integer> eventCount;

    EventRepository(Application application) {
        AppDatabase db = AppDatabase.getDbInstance(application);
        eventDao = db.eventDao();
        allEvents = eventDao.getAllEvents();
        eventCount = eventDao.getEventCount();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

    public LiveData<Integer> getEventCount() {
        return eventCount;
    }

    LiveData<Event> getByEventID(int eventID) {
        return eventDao.findByEventID(eventID);
    }

    LiveData<Event> getLastEvent() {
        return eventDao.getLastEvent();
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insertEvent(Event event) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            eventDao.insertEvent(event);
        });
    }

    void deleteEvent(int uid) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            eventDao.delete(uid);
        });
    }
}
