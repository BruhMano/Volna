package com.example.test_android.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("movies")
    private List<Movie> movies;

    public MovieResponse(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "FilmResponse{" +
                "movies=" + movies +
                '}';
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
