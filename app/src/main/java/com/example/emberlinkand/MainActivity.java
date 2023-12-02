package com.example.emberlinkand;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button CreateEventBtn = findViewById(R.id.CreateEventButton);
        Button DashboardButton = findViewById(R.id.DashBoardButton);
        Button EventListButton = findViewById(R.id.EventListButton);



        CreateEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SecondActivity
                Intent intent = new Intent(MainActivity.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });

        EventListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SecondActivity
                Intent intent = new Intent(MainActivity.this, EventListActivity.class);
                startActivity(intent);
            }
        });

        DashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SecondActivity
                Intent intent = new Intent(MainActivity.this, DashBoardActivity.class);
                startActivity(intent);
            }
        });

    }
}