package com.example.test_android.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_android.R;
import com.example.test_android.adapters.FilmAdapter;
import com.example.test_android.viewmodels.FavoriteFilmsViewModel;

public class FavoriteFilmsActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, FavoriteFilmsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movies);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMovies);
        FilmAdapter moviesAdapter = new FilmAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(moviesAdapter);

        FavoriteFilmsViewModel model = new ViewModelProvider(this)
                .get(FavoriteFilmsViewModel.class);

        model.getAllMoviesDb().observe(this, moviesAdapter::setFilms);

        moviesAdapter.setOnClickListener((movie) -> {
            Intent detailedMovieView = AboutFilmsActivity.createIntent(FavoriteFilmsActivity.this, movie);
            startActivity(detailedMovieView);
        });
    }
}