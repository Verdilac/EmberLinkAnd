package com.example.emberlinkand;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emberlinkand.DB.AppDatabase;
import com.example.emberlinkand.DB.Event;

import java.util.List;

public class EventListActivity extends AppCompatActivity {

    private EventListAdapter eventListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);


        Button AddNewUserBtn = findViewById(R.id.AddNewEventBtn);


        AddNewUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(EventListActivity.this,CreateEventActivity.class),100);
            }
        });

        initRecyclerView();

        loadEventList();

    }


    private  void  initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration  = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        eventListAdapter = new EventListAdapter(this);

        recyclerView.setAdapter(eventListAdapter);

    }

    private  void loadEventList(){
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<Event> eventList = db.eventDao().getAllEvents();
        eventListAdapter.setEventList(eventList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 100){
            loadEventList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}