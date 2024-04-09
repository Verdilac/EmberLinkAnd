package com.example.emberlinkand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emberlinkand.DB.AppDatabase;
import com.example.emberlinkand.DB.Event;
import com.example.emberlinkand.DB.EventViewModel;

import java.util.ArrayList;
import java.util.List;

public class DashBoardActivity extends AppCompatActivity implements EventListItemInterface {

    private EventListAdapter eventListAdapter;
    private EventViewModel eventViewModel;

    private NotificationGenerator mNotificationGenerator;
    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        initRecyclerView();
        listenEventList();

        TextView createEventTextView = findViewById(R.id.dashBoardCreateEventBtn);
        TextView seeAllEventList = findViewById(R.id.seeAllEventsBtn);

        // Badge
        ImageView badgeImage = findViewById(R.id.badgeImage);
        TextView badgeText = findViewById(R.id.badgeText);


        // Badge Intent
        View.OnClickListener badgeClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to BadgeInventory.class
                Intent intent = new Intent(getApplicationContext(), BadgeInventory.class);
                startActivity(intent);
            }
        };

        badgeImage.setOnClickListener(badgeClickListener);
        badgeText.setOnClickListener(badgeClickListener);

        createEventTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardActivity.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });

        seeAllEventList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardActivity.this, EventListActivity.class);
                startActivity(intent);
            }
        });

        mNotificationGenerator = new NotificationGenerator(DashBoardActivity.this);
        mHandler = new Handler(Looper.getMainLooper());

        setupEventReminderNotification();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        eventListAdapter = new EventListAdapter(this, this);

        recyclerView.setAdapter(eventListAdapter);
    }

    public void listenEventList() {
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        eventViewModel.getAllEvents().observe(this, eventList -> {
            // Update the cached copy of the movies in the adapter.
            eventListAdapter.setEventList(eventList);
        });
    }

    @Override
    public void onEventDetailsClick(int position) {
        Intent intent = new Intent(DashBoardActivity.this, EventDetailsActivity.class);
        intent.putExtra("EVENT_ID", eventListAdapter.getEventId(position));
        startActivity(intent);
    }

    private void setupEventReminderNotification() {
        // Runnable to send notification every 30 seconds

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                eventViewModel.getLastEvent().observe(DashBoardActivity.this, event -> {

                    if (event != null) {
                        // Use event name and time to trigger notification
                        String notificationDescriptionText = getString(R.string.reminder_for) + " " + event.eventName + " " + getString(R.string.on) + " " + event.eventTime;

                        // Use event name and time to trigger notification
                        mNotificationGenerator.sendNotification(
                                getString(R.string.upcoming_event),
                                notificationDescriptionText,
                                R.drawable.notification_icon
                        );
                    } else {
                        // No events found, handle accordingly (e.g., show a message or log)
                        Log.d("Notification", "No events found");
                    }
                });

                // Schedule next execution after 5 minutes (300,000 milliseconds)
                mHandler.postDelayed(this, 5 * 60 * 1000);
            }
        };

        // Start sending notification
        mHandler.postDelayed(runnable, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the callback when activity is destroyed to prevent memory leaks
        mHandler.removeCallbacksAndMessages(null);
    }
}