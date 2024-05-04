package com.example.test_android.api;

import com.example.test_android.MovieResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhpService {
    @GET("main_screeen.json")
    Single<MovieResponse> loadMovies();
}
