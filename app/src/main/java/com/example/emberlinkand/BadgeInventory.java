package com.example.emberlinkand;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BadgeInventory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BadgeAdapter badgeAdapter;

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
        badgeAdapter = new BadgeAdapter(getBadgeList(), this);
        recyclerView.setAdapter(badgeAdapter);
    }

    // Dummy data for testing
    private List<Badge> getBadgeList() {
        List<Badge> badges = new ArrayList<>();
        badges.add(new Badge(R.drawable.regular_badge_photo));
        // Add more badges as needed
        return badges;
    }
}
