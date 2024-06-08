package com.example.test_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_android.viewmodels.MainAllMoviesViewModel;
import com.example.test_android.viewmodels.MainComedyViewModel;
import com.example.test_android.viewmodels.MainFamilyViewModel;
import com.example.test_android.viewmodels.MovieAllMoviesAdapter;
import com.example.test_android.viewmodels.MovieComedyAdapter;
import com.example.test_android.viewmodels.MovieFamilyAdapter;

public class MainActivity extends AppCompatActivity {
    private MainAllMoviesViewModel mainAllMoviesViewModel;
    private MovieAllMoviesAdapter movieAllMoviesAdapter;
    private RecyclerView recyclerAllMoviesView;
    private MainComedyViewModel mainComedyViewModel;
    private MovieComedyAdapter movieComedyAdapter;
    private RecyclerView recyclerComedyView;
    private MainFamilyViewModel mainFamilyViewModel;
    private MovieFamilyAdapter movieFamilyAdapter;
    private RecyclerView recyclerFamilyView;
    private ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.init();

        recyclerAllMoviesView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

        mainAllMoviesViewModel.getFilmResponseLiveData().observe(this,
                films -> movieAllMoviesAdapter.setMovies(films));

        movieAllMoviesAdapter.setOnReachEndListener(() -> {
            mainAllMoviesViewModel.loadFilms();
        });

        mainAllMoviesViewModel.getIsLoadingMovies().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });

        movieAllMoviesAdapter.setOnClickListener((movie) -> {
            Intent detailedMovieView = CinemaActivity.createIntent(MainActivity.this, movie);
            startActivity(detailedMovieView);
        });
        //----------------
        recyclerComedyView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

        mainComedyViewModel.getFilmResponseLiveData().observe(this,
                films -> movieComedyAdapter.setMovies(films));

        movieComedyAdapter.setOnReachEndListener(() -> {
            mainComedyViewModel.loadFilms();
        });

        mainComedyViewModel.getIsLoadingMovies().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });

        movieComedyAdapter.setOnClickListener((movie) -> {
            Intent detailedMovieView = CinemaActivity.createIntent(MainActivity.this, movie);
            startActivity(detailedMovieView);
        });
        //----------------
        recyclerFamilyView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

        mainFamilyViewModel.getFilmResponseLiveData().observe(this,
                films -> movieFamilyAdapter.setMovies(films));

        movieFamilyAdapter.setOnReachEndListener(() -> {
            mainFamilyViewModel.loadFilms();
        });

        mainFamilyViewModel.getIsLoadingMovies().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });

        movieFamilyAdapter.setOnClickListener((movie) -> {
            Intent detailedMovieView = CinemaActivity.createIntent(MainActivity.this, movie);
            startActivity(detailedMovieView);
        });
    }

    private void init() {
        progressBar = findViewById(R.id.progress_circular_loading_MainActivity);

        recyclerAllMoviesView = findViewById(R.id.recycler_allMoviesMainActivity);
        movieAllMoviesAdapter = new MovieAllMoviesAdapter();
        recyclerAllMoviesView.setAdapter(movieAllMoviesAdapter);
        mainAllMoviesViewModel = new ViewModelProvider(this).get(MainAllMoviesViewModel.class);

        recyclerComedyView = findViewById(R.id.recycler_comedyMainActivity);
        movieComedyAdapter = new MovieComedyAdapter();
        recyclerComedyView.setAdapter(movieComedyAdapter);
        mainComedyViewModel = new ViewModelProvider(this).get(MainComedyViewModel.class);

        recyclerFamilyView = findViewById(R.id.recycler_familyMainActivity);
        movieFamilyAdapter = new MovieFamilyAdapter();
        recyclerFamilyView.setAdapter(movieFamilyAdapter);
        mainFamilyViewModel = new ViewModelProvider(this).get(MainFamilyViewModel.class);
    }
}
