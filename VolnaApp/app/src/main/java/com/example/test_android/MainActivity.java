package com.example.test_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void to_cinema(View viev){
        Intent intent = new Intent(this, CinemaActivity.class);
        startActivity(intent);
    }

}
