package com.example.newsapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class LaunchActivity extends AppCompatActivity {

    private static final long DISPLAY_DURATION = 1500; // Duration in milliseconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ProgressBar loadingProgressBar = findViewById(R.id.loadingProgressBar);

        // Start the loading animation
        loadingProgressBar.setIndeterminate(true);

        // Use a Handler to delay the transition to the next activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the next activity here (e.g., MainActivity)
                Intent intent = new Intent(LaunchActivity.this, login.class);
                startActivity(intent);
                finish();
            }
        }, DISPLAY_DURATION);

    }
}