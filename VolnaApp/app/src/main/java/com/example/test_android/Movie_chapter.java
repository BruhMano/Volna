package com.example.test_android;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_android.viewmodels.MovieChapterAllMovies;
import com.example.test_android.viewmodels.MovieChapterAllMoviesAdapter;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;

public class Movie_chapter extends AppCompatActivity {
    private MovieChapterAllMovies movieChapterAllMovies;
    private MovieChapterAllMoviesAdapter movieChapterAllMoviesAdapter;
    private RecyclerView recyclerAllMoviesView;
    private ProgressBar progressBar;

    String[] genres = {"фантастика", "ужасы", "криминал", "исторический", "мультфильм", "приключение", "комедия", "драма", "боевик"};
    boolean[] checkedItems = {false, false, false, false, false, false, false, false, false};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_chapter);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_chapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genres);
        spinner.setAdapter(adapter);

        spinner.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Выберите жанр");
                builder.setMultiChoiceItems(genres, checkedItems, (dialog, which, isChecked) -> {
                    checkedItems[which] = isChecked;
                });

                builder.setPositiveButton("OK", (dialog, which) -> {
                    for (int i = 0; i < genres.length; i++) {
                        if (checkedItems[i]&& genres[i].equals("фантастика")) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);

                            this.init();

                            recyclerAllMoviesView.setLayoutManager(new GridLayoutManager(this, 3));

                            movieChapterAllMovies.getFilmResponseLiveData().observe(this,
                                    films -> movieChapterAllMoviesAdapter.setMoviesFantasy(films));

                            movieChapterAllMoviesAdapter.setOnReachEndListener(() -> {
                                movieChapterAllMovies.loadFilms();
                            });

                            movieChapterAllMovies.getIsLoadingMovies().observe(this, isLoading -> {
                                if (isLoading) progressBar.setVisibility(View.VISIBLE);
                                else progressBar.setVisibility(View.GONE);
                            });

                            movieChapterAllMoviesAdapter.setOnClickListener((movie) -> {
                                Intent detailedMovieView = CinemaActivity.createIntent(Movie_chapter.this, movie);
                                startActivity(detailedMovieView);
                            });
                        }
                        if (checkedItems[i]&& genres[i].equals("ужасы")) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);

                            this.init();

                            recyclerAllMoviesView.setLayoutManager(new GridLayoutManager(this, 3));

                            movieChapterAllMovies.getFilmResponseLiveData().observe(this,
                                    films -> movieChapterAllMoviesAdapter.setMoviesHorror(films));

                            movieChapterAllMoviesAdapter.setOnReachEndListener(() -> {
                                movieChapterAllMovies.loadFilms();
                            });

                            movieChapterAllMovies.getIsLoadingMovies().observe(this, isLoading -> {
                                if (isLoading) progressBar.setVisibility(View.VISIBLE);
                                else progressBar.setVisibility(View.GONE);
                            });

                            movieChapterAllMoviesAdapter.setOnClickListener((movie) -> {
                                Intent detailedMovieView = CinemaActivity.createIntent(Movie_chapter.this, movie);
                                startActivity(detailedMovieView);
                            });
                        }
                        if (checkedItems[i]&& genres[i].equals("криминал")) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);

                            this.init();

                            recyclerAllMoviesView.setLayoutManager(new GridLayoutManager(this, 3));

                            movieChapterAllMovies.getFilmResponseLiveData().observe(this,
                                    films -> movieChapterAllMoviesAdapter.setMoviesCrime(films));

                            movieChapterAllMoviesAdapter.setOnReachEndListener(() -> {
                                movieChapterAllMovies.loadFilms();
                            });

                            movieChapterAllMovies.getIsLoadingMovies().observe(this, isLoading -> {
                                if (isLoading) progressBar.setVisibility(View.VISIBLE);
                                else progressBar.setVisibility(View.GONE);
                            });

                            movieChapterAllMoviesAdapter.setOnClickListener((movie) -> {
                                Intent detailedMovieView = CinemaActivity.createIntent(Movie_chapter.this, movie);
                                startActivity(detailedMovieView);
                            });
                        }
                        if (checkedItems[i]&& genres[i].equals("исторический")) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);

                            this.init();

                            recyclerAllMoviesView.setLayoutManager(new GridLayoutManager(this, 3));

                            movieChapterAllMovies.getFilmResponseLiveData().observe(this,
                                    films -> movieChapterAllMoviesAdapter.setMoviesHistory(films));

                            movieChapterAllMoviesAdapter.setOnReachEndListener(() -> {
                                movieChapterAllMovies.loadFilms();
                            });

                            movieChapterAllMovies.getIsLoadingMovies().observe(this, isLoading -> {
                                if (isLoading) progressBar.setVisibility(View.VISIBLE);
                                else progressBar.setVisibility(View.GONE);
                            });

                            movieChapterAllMoviesAdapter.setOnClickListener((movie) -> {
                                Intent detailedMovieView = CinemaActivity.createIntent(Movie_chapter.this, movie);
                                startActivity(detailedMovieView);
                            });
                        }
                        if (checkedItems[i]&& genres[i].equals("мультфильм")) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);

                            this.init();

                            recyclerAllMoviesView.setLayoutManager(new GridLayoutManager(this, 3));

                            movieChapterAllMovies.getFilmResponseLiveData().observe(this,
                                    films -> movieChapterAllMoviesAdapter.setMoviesAnimation(films));

                            movieChapterAllMoviesAdapter.setOnReachEndListener(() -> {
                                movieChapterAllMovies.loadFilms();
                            });

                            movieChapterAllMovies.getIsLoadingMovies().observe(this, isLoading -> {
                                if (isLoading) progressBar.setVisibility(View.VISIBLE);
                                else progressBar.setVisibility(View.GONE);
                            });

                            movieChapterAllMoviesAdapter.setOnClickListener((movie) -> {
                                Intent detailedMovieView = CinemaActivity.createIntent(Movie_chapter.this, movie);
                                startActivity(detailedMovieView);
                            });
                        }
                        if (checkedItems[i]&& genres[i].equals("приключение")) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);

                            this.init();

                            recyclerAllMoviesView.setLayoutManager(new GridLayoutManager(this, 3));

                            movieChapterAllMovies.getFilmResponseLiveData().observe(this,
                                    films -> movieChapterAllMoviesAdapter.setMoviesAdventure(films));

                            movieChapterAllMoviesAdapter.setOnReachEndListener(() -> {
                                movieChapterAllMovies.loadFilms();
                            });

                            movieChapterAllMovies.getIsLoadingMovies().observe(this, isLoading -> {
                                if (isLoading) progressBar.setVisibility(View.VISIBLE);
                                else progressBar.setVisibility(View.GONE);
                            });

                            movieChapterAllMoviesAdapter.setOnClickListener((movie) -> {
                                Intent detailedMovieView = CinemaActivity.createIntent(Movie_chapter.this, movie);
                                startActivity(detailedMovieView);
                            });
                        }
                        if (checkedItems[i]&& genres[i].equals("комедия")) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);

                            this.init();

                            recyclerAllMoviesView.setLayoutManager(new GridLayoutManager(this, 3));

                            movieChapterAllMovies.getFilmResponseLiveData().observe(this,
                                    films -> movieChapterAllMoviesAdapter.setMoviesComedy(films));

                            movieChapterAllMoviesAdapter.setOnReachEndListener(() -> {
                                movieChapterAllMovies.loadFilms();
                            });

                            movieChapterAllMovies.getIsLoadingMovies().observe(this, isLoading -> {
                                if (isLoading) progressBar.setVisibility(View.VISIBLE);
                                else progressBar.setVisibility(View.GONE);
                            });

                            movieChapterAllMoviesAdapter.setOnClickListener((movie) -> {
                                Intent detailedMovieView = CinemaActivity.createIntent(Movie_chapter.this, movie);
                                startActivity(detailedMovieView);
                            });
                        }
                        if (checkedItems[i]&& genres[i].equals("драма")) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);

                            this.init();

                            recyclerAllMoviesView.setLayoutManager(new GridLayoutManager(this, 3));

                            movieChapterAllMovies.getFilmResponseLiveData().observe(this,
                                    films -> movieChapterAllMoviesAdapter.setMoviesDrama(films));

                            movieChapterAllMoviesAdapter.setOnReachEndListener(() -> {
                                movieChapterAllMovies.loadFilms();
                            });

                            movieChapterAllMovies.getIsLoadingMovies().observe(this, isLoading -> {
                                if (isLoading) progressBar.setVisibility(View.VISIBLE);
                                else progressBar.setVisibility(View.GONE);
                            });

                            movieChapterAllMoviesAdapter.setOnClickListener((movie) -> {
                                Intent detailedMovieView = CinemaActivity.createIntent(Movie_chapter.this, movie);
                                startActivity(detailedMovieView);
                            });
                        }
                        if (checkedItems[i]&& genres[i].equals("боевик")) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);

                            this.init();

                            recyclerAllMoviesView.setLayoutManager(new GridLayoutManager(this, 3));


                            movieChapterAllMovies.getFilmResponseLiveData().observe(this,
                                    films -> movieChapterAllMoviesAdapter.setMoviesAction(films));

                            movieChapterAllMoviesAdapter.setOnReachEndListener(() -> {
                                movieChapterAllMovies.loadFilms();
                            });

                            movieChapterAllMovies.getIsLoadingMovies().observe(this, isLoading -> {
                                if (isLoading) progressBar.setVisibility(View.VISIBLE);
                                else progressBar.setVisibility(View.GONE);
                            });

                            movieChapterAllMoviesAdapter.setOnClickListener((movie) -> {
                                Intent detailedMovieView = CinemaActivity.createIntent(Movie_chapter.this, movie);
                                startActivity(detailedMovieView);
                            });
                        }
                    }
                });

                builder.setNegativeButton("Отмена", null);
                builder.show();
            }
            return true;
        });
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, genres) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;
                if (getItem(position).equals("фантастика")) {
                    textView.setText("Жанры");
                }
                return view;
            }


            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                if (getItem(position).equals("фантастика")) {
                    textView.setText("Жанры");
                }
                return view;
            }
        };
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.init();

        recyclerAllMoviesView.setLayoutManager(new GridLayoutManager(this, 3));

        movieChapterAllMovies.getFilmResponseLiveData().observe(this,
                films -> movieChapterAllMoviesAdapter.setMovies(films));

        movieChapterAllMoviesAdapter.setOnReachEndListener(() -> {
            movieChapterAllMovies.loadFilms();
        });

        movieChapterAllMovies.getIsLoadingMovies().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });

        movieChapterAllMoviesAdapter.setOnClickListener((movie) -> {
            Intent detailedMovieView = CinemaActivity.createIntent(Movie_chapter.this, movie);
            startActivity(detailedMovieView);
        });



    }

    private void init() {
        progressBar = findViewById(R.id.progress_circular_loading_MovieChapter);

        recyclerAllMoviesView = findViewById(R.id.recycler_allMoviesMovieChapter);
        movieChapterAllMoviesAdapter = new MovieChapterAllMoviesAdapter();
        recyclerAllMoviesView.setAdapter(movieChapterAllMoviesAdapter);
        movieChapterAllMovies = new ViewModelProvider(this).get(MovieChapterAllMovies.class);

    }
    public void move_to_main(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity (intent);
    }
}
