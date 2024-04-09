package com.example.emberlinkand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emberlinkand.DB.EventViewModel;

import java.util.ArrayList;
import java.util.List;

public class BadgeInventory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BadgeAdapter badgeAdapter;
    private EventViewModel eventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge_inventory);

        ImageView backButton = findViewById(R.id.toolbar_back_icon);
        TextView toolbarText = findViewById(R.id.toolbar_text);
        toolbarText.setText("All Badges");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BadgeInventory.this, DashBoardActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        eventViewModel.getEventCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                badgeAdapter = new BadgeAdapter(getBadgeList(count), BadgeInventory.this);
                recyclerView.setAdapter(badgeAdapter);
            }
        });
    }

    // Generate badge list based on event count
    private List<Badge> getBadgeList(int eventCount) {
        List<Badge> badges = new ArrayList<>();
        if (eventCount >= 5) {
            badges.add(new Badge(R.drawable.regular_badge_photo));
            badges.add(new Badge(R.drawable.premium_badge_photo));
        } else if (eventCount >= 1 && eventCount <= 4) {
            badges.add(new Badge(R.drawable.regular_badge_photo));
        }
        // You can add more conditions here if needed
        return badges;
    }
}
