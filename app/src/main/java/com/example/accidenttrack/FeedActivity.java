package com.example.accidenttrack;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        FloatingActionButton fabAddIncident = findViewById(R.id.fabAddIncident);
        fabAddIncident.setOnClickListener(v -> {
            Intent intent = new Intent(FeedActivity.this, PostIncidentActivity.class);
            startActivity(intent);
        });
    }
}
