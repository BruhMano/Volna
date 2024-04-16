package com.example.test_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class cinema extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("json");
        }
    }
}