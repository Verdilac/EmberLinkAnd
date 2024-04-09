package com.example.emberlinkand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emberlinkand.DB.AppDatabase;
import com.example.emberlinkand.DB.Event;
import com.example.emberlinkand.DB.EventViewModel;

public class CreateEventActivity extends AppCompatActivity {

    private EventViewModel eventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        final EditText organizerNameInput = findViewById(R.id.editTextName);
        final EditText eventNameInput = findViewById(R.id.editTextEventName);
        final EditText eventParticipantLimitInput = findViewById(R.id.editTextParticipantsLimit);
        final EditText eventTimeInput = findViewById(R.id.editTextTime);
        final EditText eventVenueInput = findViewById(R.id.editTextVenue);
        final EditText eventDescriptionInput = findViewById(R.id.editTextDescription);
        final EditText eventTagInput = findViewById(R.id.editTextTags);
        Button submitButton = findViewById(R.id.SubmitBtn);

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        // Observe event count
        eventViewModel.getEventCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                // Update toolbar text to include event count
                TextView toolbarText = findViewById(R.id.toolbar_text);
                String toolbarTitle = "Add New Event: " + count;
                toolbarText.setText(toolbarTitle);
            }
        });

        //toolbar back button
        ImageView backButton = findViewById(R.id.toolbar_back_icon);
//        TextView toolbarText = findViewById(R.id.toolbar_text);
//        toolbarText.setText("Add New Event");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateEventActivity.this, DashBoardActivity.class);
                startActivity(intent);
            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String organizerName = organizerNameInput.getText().toString();
                final String eventName = eventNameInput.getText().toString();
                final String eventParticipantLimit = eventParticipantLimitInput.getText().toString();
                final String time = eventTimeInput.getText().toString();
                final String venue = eventVenueInput.getText().toString();
                final String description = eventDescriptionInput.getText().toString();
                final String tag = eventTagInput.getText().toString();

                saveNewEvent(organizerName, eventName, eventParticipantLimit, time, venue, description, tag);

                // Check the count of events
                eventViewModel.getEventCount().observe(CreateEventActivity.this, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer count) {
                        if (count == 1) {
                            Intent intent = new Intent(CreateEventActivity.this, RegularBadge.class);
                            startActivity(intent);
                        } else if (count == 5) {
                            Intent intent = new Intent(CreateEventActivity.this, PremiumBadge.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(CreateEventActivity.this, DashBoardActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }

    private  void saveNewEvent(String organizerName,String eventName,String eventParticipantLimit,String time,String venue,String description,String tag){
        EventViewModel eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);;
        Event event = new Event();
        event.organizerName = organizerName;
        event.eventName = eventName;
        event.eventDescription = description;
        event.eventTime = time;
        event.participantLimit = eventParticipantLimit;
        event.eventvenue = venue;
        event.eventtag = tag;

        eventViewModel.insertEvent(event);

        finish();
    }

}