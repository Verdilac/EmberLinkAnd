package com.example.emberlinkand;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.emberlinkand.DB.AppDatabase;
import com.example.emberlinkand.DB.Event;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        final EditText organizerNameInput = findViewById(R.id.editTextName);
        final EditText eventNameInput = findViewById(R.id.editTextEventName);

        Button submitButton = findViewById(R.id.SubmitBtn);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewEvent(organizerNameInput.getText().toString(),eventNameInput.getText().toString());
            }
        });
    }





    private  void saveNewEvent(String organizerName,String eventName){
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        Event event = new Event();
        event.organizerName = organizerName;
        event.eventName = eventName;

        db.eventDao().insertEvent(event);

        finish();

    }

}