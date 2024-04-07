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

    private  void  initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration  = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
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
        String eventTime = "13:00";
        String eventName = "Dancing";
        String notificationDescriptionText = getString(R.string.reminder_for) + eventName + getString(R.string.on) + eventTime;
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mNotificationGenerator.sendNotification(
                        getString(R.string.upcoming_event),
                        notificationDescriptionText,
                        R.drawable.notification_icon
                );
                mHandler.postDelayed(this, 30 * 1000); // 30 seconds
            }
        };

        // Start sending notification
        mHandler.postDelayed(mRunnable, 0);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the callback when activity is destroyed to prevent memory leaks
        mHandler.removeCallbacks(mRunnable);
    }
}