package com.example.test_android.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test_android.R;
import com.example.test_android.adapters.ReviewAdapter;
import com.example.test_android.models.Film;
import com.example.test_android.viewmodels.AboutFilmsViewModel;

public class AboutFilmsActivity extends AppCompatActivity {
    private final static String EXTRA_MOVIE = "film";
    private ImageView pictureMovieAboutMovieActivity;
    private TextView titleMovieAboutMovieActivity;
    private TextView yearMovieAboutMovieActivity;
    private TextView descriptionMovieAboutMovieActivity;
    private AboutFilmsViewModel model;
    private RecyclerView reviewRecycler;
    private ImageView imageViewStar;
    private ReviewAdapter reviewAdapter;

    public static Intent createIntent(Context context, Film movie) {
        Intent intent = new Intent(context, AboutFilmsActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_movie_view);

        initViews();

        Film movie = (Film) getIntent().getSerializableExtra(EXTRA_MOVIE);

        model.loadReviews(movie.getId());

        Glide.with(this)
                .load(movie.getPoster().getPreviewUrl())
                .into(pictureMovieAboutMovieActivity);

        model.getReviewLiveData().observe(this, reviews -> reviewAdapter.setReviews(reviews));

        Drawable starOff = ContextCompat.getDrawable(AboutFilmsActivity.this, android.R.drawable.star_big_off);
        Drawable starOn = ContextCompat.getDrawable(AboutFilmsActivity.this, android.R.drawable.star_big_on);

        model.getFavoriteFilm(movie.getId()).observe(this, movieFromDb -> {
            if (movieFromDb == null) {
                imageViewStar.setImageDrawable(starOff);
                imageViewStar.setOnClickListener(v -> model.insertMovieDb(movie));
            } else {
                imageViewStar.setImageDrawable(starOn);
                imageViewStar.setOnClickListener(v -> model.removeMovieDb(movie.getId()));
            }
        });

        titleMovieAboutMovieActivity.setText(movie.getName());
        yearMovieAboutMovieActivity.setText(String.valueOf(movie.getYear()));
        descriptionMovieAboutMovieActivity.setText(movie.getDescription());
    }

    private void initViews() {

        model = new ViewModelProvider(this).get(AboutFilmsViewModel.class);
        pictureMovieAboutMovieActivity = findViewById(R.id.pictureMovieAboutMovieActivity);
        titleMovieAboutMovieActivity = findViewById(R.id.titleMovieAboutMovieActivity);
        yearMovieAboutMovieActivity = findViewById(R.id.yearMovieAboutMovieActivity);
        descriptionMovieAboutMovieActivity = findViewById(R.id.descriptionMovieAboutMovieActivity);
        imageViewStar = findViewById(R.id.favoriteStar);

        reviewRecycler = findViewById(R.id.reviewRecyclerView);
        reviewAdapter = new ReviewAdapter();
        reviewRecycler.setAdapter(reviewAdapter);
    }
}