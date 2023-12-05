package com.example.emberlinkand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emberlinkand.DB.Event;
import com.example.emberlinkand.DB.EventViewModel;

public class EventDetailsActivity extends AppCompatActivity {
    private int eventId;
    private EventViewModel eventViewModel;

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

        // Retrieve data from the intent
        eventId = getIntent().getIntExtra("EVENT_ID", -1);
        // Initialize ViewModel
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        // Observe changes from the database
        eventViewModel.getByEventID(eventId).observe(this, event -> {
            initializeViews(event);
        });
    }

    private void initializeViews(Event event) {
        Log.d("Event", event.eventName);
    }
}