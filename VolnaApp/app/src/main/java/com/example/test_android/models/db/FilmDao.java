package com.example.test_android.models.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.test_android.models.Film;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface FilmDao {

    @Query("SELECT * FROM volna_films")
    LiveData<List<Film>> getAllFavoriteFilms();

    @Query("SELECT * FROM volna_films WHERE id = :id")
    LiveData<Film> getFavoriteFilmById(int id);

    @Insert
    Completable insertFilm(Film film);

    @Query("DELETE FROM volna_films WHERE id = :id")
    Completable removeFilm(int id);
}
