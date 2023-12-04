package com.example.emberlinkand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.emberlinkand.DB.AppDatabase;
import com.example.emberlinkand.DB.Event;

import java.util.ArrayList;
import java.util.List;

public class DashBoardActivity extends AppCompatActivity {

    private EventListAdapter eventListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
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

    public void loadEventList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<Event> eventList = db.eventDao().getAllEvents();
        eventListAdapter.setEventList(eventList);
    }

}