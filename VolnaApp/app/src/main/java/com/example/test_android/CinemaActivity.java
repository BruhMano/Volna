package com.example.test_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CinemaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);
    }
    public void to_video(View viev){
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }
}