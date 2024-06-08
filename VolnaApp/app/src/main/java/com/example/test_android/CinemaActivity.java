package com.example.test_android;

import static com.example.test_android.api.UrlConstant.BASE_URL;
import static com.example.test_android.api.UrlConstant.PATH_FILM;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.test_android.model.Movie;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class CinemaActivity extends AppCompatActivity {
    private final static String EXTRA_MOVIE = "film";

    private boolean isDownloaded = false;
    String link;
    String link_name;
    private ImageView pictureMovieAboutMovieActivity;
    private TextView nameMovieAboutMovieActivity;
    private TextView ratingMovieAboutMovieActivity;
    private TextView ageMovieAboutMovieActivity;
    private TextView durationMovieAboutMovieActivity;
    private TextView genreMovieAboutMovieActivity;
    private TextView descriptionsMovieAboutMovieActivity;

    public static Intent createIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, CinemaActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }
    public void isFilmExists(){
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "volna");
        File film = new File(directory,movie.getLink());
        if (film.exists()){
            isDownloaded = true;
            link = film.getAbsolutePath();
            Log.d(this.getClass().getSimpleName(), "URL Film: " + film.getAbsolutePath());
            ImageButton downloadButton = findViewById(R.id.download_video);
            downloadButton.setOnClickListener(this::to_video);
            ImageView imageView = findViewById(R.id.download_video);
            imageView.setImageResource(R.drawable.play_download);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cinema);

        initViews();

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        isFilmExists();
        Glide.with(this)
                .load(movie.getVertical_poster())
                .into(pictureMovieAboutMovieActivity);

        nameMovieAboutMovieActivity.setText(movie.getTitle());
        ratingMovieAboutMovieActivity.setText(String.valueOf(movie.getImdb()));
        ageMovieAboutMovieActivity.setText(String.valueOf(movie.getAge()) + "+");
        durationMovieAboutMovieActivity.setText(String.valueOf(movie.getDuration()) + " мин.");
        genreMovieAboutMovieActivity.setText(movie.getGenre());
        descriptionsMovieAboutMovieActivity.setText(movie.getDesc());
    }

    private void initViews() {
        pictureMovieAboutMovieActivity = findViewById(R.id.imageCinema);
        nameMovieAboutMovieActivity = findViewById(R.id.name_film);
        ratingMovieAboutMovieActivity = findViewById(R.id.rating_value);
        ageMovieAboutMovieActivity = findViewById(R.id.age);
        durationMovieAboutMovieActivity = findViewById(R.id.duration);
        genreMovieAboutMovieActivity = findViewById(R.id.genre);
        descriptionsMovieAboutMovieActivity = findViewById(R.id.descriptions);
    }

    public void to_video(View view){
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        if (!isDownloaded){
            link = BASE_URL + PATH_FILM + movie.getLink();
            Log.d(this.getClass().getSimpleName(), "URL Film: " + link);
        }
        Intent intent = new Intent(CinemaActivity.this, VideoActivity.class);
        intent.putExtra("link", link);
        CinemaActivity.this.startActivity(intent);
    }

    public void download(View view) throws JSONException {

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        String link = BASE_URL + PATH_FILM + movie.getLink();
        Log.d(this.getClass().getSimpleName(), "URL Film: " + link);
        new VideoDownloader(this, movie.getLink()).execute(link);
        isFilmExists();
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}