package com.example.test_android.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.test_android.models.Film;
import com.example.test_android.models.db.FilmDao;
import com.example.test_android.models.db.FilmDatabase;

import java.util.List;

public class FavoriteFilmsViewModel extends AboutFilmsViewModel {
    private final FilmDao filmDao;

    public FavoriteFilmsViewModel(@NonNull Application application) {
        super(application);
        filmDao = FilmDatabase.getInstance(application).movieDao();
    }

    public LiveData<List<Film>> getAllMoviesDb() {
        return filmDao.getAllFavoriteFilms();
    }
}
