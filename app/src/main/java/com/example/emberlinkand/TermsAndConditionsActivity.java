package com.example.emberlinkand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TermsAndConditionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);
    }

    public void onClickAccept(View view) {
        Intent intent = new Intent(TermsAndConditionsActivity.this, DashBoardActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickDecline(View view) {
        Intent intent = new Intent(TermsAndConditionsActivity.this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}