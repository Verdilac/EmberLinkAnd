package com.example.emberlinkand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EventDetailsActivity extends AppCompatActivity {

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
    }
}