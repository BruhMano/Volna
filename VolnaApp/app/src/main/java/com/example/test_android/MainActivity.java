package com.example.test_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ProgressBar;

import com.example.test_android.viewmodels.MainComedyViewModel;
import com.example.test_android.viewmodels.MainFamilyViewModel;
import com.example.test_android.viewmodels.MainHonorViewModel;
import com.example.test_android.viewmodels.MovieComedyAdapter;
import com.example.test_android.viewmodels.MovieFamilyAdapter;
import com.example.test_android.viewmodels.MovieHonorAdapter;

public class MainActivity extends AppCompatActivity {

    private MainHonorViewModel mainHonorViewModel;
    private MovieHonorAdapter movieHonorAdapter;
    private RecyclerView recyclerHonorView;
    private MainComedyViewModel mainComedyViewModel;
    private MovieComedyAdapter movieComedyAdapter;
    private RecyclerView recyclerComedyView;
    private MainFamilyViewModel mainFamilyViewModel;
    private MovieFamilyAdapter movieFamilyAdapter;
    private RecyclerView recyclerFamilyView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.init();

        recyclerHonorView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

        mainHonorViewModel.getFilmResponseLiveData().observe(this,
                films -> movieHonorAdapter.setMovies(films));

        movieHonorAdapter.setOnReachEndListener(() -> {
            mainHonorViewModel.loadFilms();
        });

        mainHonorViewModel.getIsLoadingMovies().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });

        movieHonorAdapter.setOnClickListener((movie) -> {
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
    /*public void to_cinema(View view){
        Intent intent = new Intent(this, CinemaActivity.class);
        startActivity(intent);
    }*/

    private void init() {
        progressBar = findViewById(R.id.progress_circular_loading_MainActivity);

        recyclerHonorView = findViewById(R.id.recycler_honorMainActivity);
        movieHonorAdapter = new MovieHonorAdapter();
        recyclerHonorView.setAdapter(movieHonorAdapter);
        mainHonorViewModel = new ViewModelProvider(this).get(MainHonorViewModel.class);

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
