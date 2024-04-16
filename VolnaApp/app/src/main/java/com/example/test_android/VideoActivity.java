package com.example.test_android;
import androidx.appcompat.app.AppCompatActivity;


import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;


import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        VideoView videoView = findViewById(R.id.videoView);
        Bundle extras = getIntent().getExtras();
        String value = "";
        if (extras != null) {
            value = extras.getString("link");
        }
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse("http://176.77.109.225/content/films/"+value);
        mediaController.setAnchorView(videoView);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }

}