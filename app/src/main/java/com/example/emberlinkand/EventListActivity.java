package com.example.emberlinkand;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emberlinkand.DB.AppDatabase;
import com.example.emberlinkand.DB.Event;
import com.example.emberlinkand.DB.EventViewModel;

import java.util.List;

public class EventListActivity extends AppCompatActivity implements EventListItemInterface {

    private EventListAdapter eventListAdapter;
    private EventViewModel eventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        initRecyclerView();
        listenEventList();


        //toolbar back button
        ImageView backButton = findViewById(R.id.toolbar_back_icon);
        TextView toolbarText = findViewById(R.id.toolbar_text);
        toolbarText.setText("See All Events");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to last screen on the stack
                finish();
            }
        });

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // No longer needed because we are actively listening to changes now
        // if(requestCode == 100){
            // loadEventList();
        // }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onEventDetailsClick(int position) {
        Intent intent = new Intent(EventListActivity.this, EventDetailsActivity.class);
        intent.putExtra("EVENT_ID", eventListAdapter.getEventId(position));
        startActivity(intent);
    }
}