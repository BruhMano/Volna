package com.example.test_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_android.adapters.FilmAdapter;
import com.example.test_android.viewmodels.MainViewModel;
import com.example.test_android.views.AboutFilmsActivity;
import com.example.test_android.views.FavoriteFilmsActivity;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private FilmAdapter filmAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.init();

        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

        mainViewModel.getFilmResponseLiveData().observe(this,
                films -> filmAdapter.setFilms(films));

        filmAdapter.setOnReachEndListener(() -> {
            mainViewModel.loadFilms();
            ;
        });

        mainViewModel.getIsLoadingMovies().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });

        filmAdapter.setOnClickListener((movie) -> {
            Intent detailedMovieView = AboutFilmsActivity.createIntent(MainActivity.this, movie);
            startActivity(detailedMovieView);
        });
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_mainActivity);
        progressBar = findViewById(R.id.progress_circular_loading_MainActivity);

        filmAdapter = new FilmAdapter();

        recyclerView.setAdapter(filmAdapter);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemFavorite) {
            Intent intent = FavoriteFilmsActivity.createIntent(MainActivity.this);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
