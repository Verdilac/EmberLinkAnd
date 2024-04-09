package com.example.emberlinkand;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class PremiumBadge extends AppCompatActivity {

    TextView premiumapp;
    LottieAnimationView premium_lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_badge);

        premiumapp = findViewById(R.id.premiumapp);
        premium_lottie = findViewById(R.id.premium_lottie);

        premiumapp.animate().translationY(-200).setDuration(2700).setStartDelay(0);
        premium_lottie.animate().translationX(2000).setDuration(2000).setStartDelay(2900);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), DashBoardActivity.class);
                startActivity(i);
            }
        }, 5000);

    }
}