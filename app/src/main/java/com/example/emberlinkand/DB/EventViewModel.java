package com.example.emberlinkand.DB;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EventViewModel extends AndroidViewModel {
    private EventRepository eventRepository;
    private final LiveData<List<Event>> allEvents;

    public EventViewModel (Application application) {
        super(application);
        eventRepository = new EventRepository(application);
        allEvents = eventRepository.getAllEvents();
    }

    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

    public LiveData<Event> getByEventID(int eventID) {
        return eventRepository.getByEventID(eventID);
    }

    public void insertEvent(Event event) {
        eventRepository.insertEvent(event);
    }
}

