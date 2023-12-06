package com.example.emberlinkand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emberlinkand.DB.Event;
import com.example.emberlinkand.DB.EventViewModel;

public class EventDetailsActivity extends AppCompatActivity {
    private int eventId;
    private EventViewModel eventViewModel;

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        //toolbar back button
        ImageView backButton = findViewById(R.id.toolbar_back_icon);
        TextView toolbarText = findViewById(R.id.toolbar_text);
        toolbarText.setText("Event details");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to last screen on the stack
                finish();
            }
        });

        listenEventDetails();
    }

    private void listenEventDetails() {
        // Retrieve data from the intent
        eventId = getIntent().getIntExtra("EVENT_ID", -1);
        // Initialize ViewModel
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        // Observe changes from the database
        eventViewModel.getByEventID(eventId).observe(this, incomingEvent -> {
            event = incomingEvent;
            initializeViews();
        });
    }

    private void initializeViews() {
        TextView eventName = findViewById(R.id.eventNameView);
        TextView organizerName = findViewById(R.id.organizerNameView);
        TextView eventParticipantLimit = findViewById(R.id.participantLimitView);
        TextView eventDescription = findViewById(R.id.eventDescriptionView);
        TextView eventTime = findViewById(R.id.eventTimeView);
        TextView eventVenue = findViewById(R.id.eventVenueView);
        TextView eventTag = findViewById(R.id.eventTagView);

        if(event != null) {
            eventName.setText(event.eventName);
            organizerName.setText(event.organizerName);
            eventParticipantLimit.setText(event.participantLimit);
            eventDescription.setText(event.eventDescription);
            eventTime.setText(event.eventTime);
            eventVenue.setText(event.eventvenue);
            eventTag.setText(event.eventtag);
        }
    }

    public void onClickDelete(View view) {
        eventViewModel.deleteEvent(event.uid);
        finish();
    }
}