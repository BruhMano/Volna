package com.example.test_android;
import androidx.appcompat.app.AppCompatActivity;


import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;


import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        VideoView videoView = findViewById(R.id.videoView);
        Bundle extras = getIntent().getExtras();
        String value = extras.getString("link");
        Log.d(this.getClass().getSimpleName(), "URL Film: " + value);
        Log.i(this.getClass().getSimpleName(), "link:" + value);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse(value);
        mediaController.setAnchorView(videoView);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }

}