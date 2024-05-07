package com.example.test_android.api;



import com.example.test_android.model.MovieResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface PhpService {
    @GET("main_screeen.php")
    Single<MovieResponse> loadMovies();
}
