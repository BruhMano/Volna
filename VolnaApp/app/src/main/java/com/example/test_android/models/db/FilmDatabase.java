package com.example.test_android.models.db;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.test_android.models.Film;

@Database(entities = {Film.class}, version = 1, exportSchema = false)
public abstract class FilmDatabase extends RoomDatabase {
    private static final String DB_NAME = "volna.db";
    private static FilmDatabase instance = null;

    public static FilmDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(application, FilmDatabase.class, DB_NAME).build();
        }
        return instance;
    }

    public abstract FilmDao movieDao();
}
