package com.example.test_android.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmResponse {

    @SerializedName("docs")
    private List<Film> films;

    public FilmResponse(List<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "FilmResponse{" +
                "movies=" + films +
                '}';
    }

    public List<Film> getFilms() {
        return films;
    }
}
