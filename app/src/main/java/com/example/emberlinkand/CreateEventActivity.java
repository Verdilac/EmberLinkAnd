package com.example.emberlinkand;

import androidx.appcompat.app.AppCompatActivity;
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

        //toolbar back button
        ImageView backButton = findViewById(R.id.toolbar_back_icon);
        TextView toolbarText = findViewById(R.id.toolbar_text);
        toolbarText.setText("Add New Event");

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
                saveNewEvent(organizerNameInput.getText().toString(),eventNameInput.getText().toString(),eventParticipantLimitInput.getText().toString(),
                        eventTimeInput.getText().toString(),eventVenueInput.getText().toString(),eventDescriptionInput.getText().toString(),
                        eventTagInput.getText().toString());

                Intent intent = new Intent(CreateEventActivity.this, RegularBadge.class);
                startActivity(intent);
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