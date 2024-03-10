package com.example.test_android.models.api;

import com.example.test_android.models.FilmResponse;
import com.example.test_android.models.reviews.ReviewResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie?token=RNVF6PQ-FP34B0G-MVHZF02-8C6DVDE&limit=25&type=movie&rating.kp=8-10")
    Single<FilmResponse> loadMovies(@Query("page") int page);

 /*  @GET("movie?token=RNVF6PQ-FP34B0G-MVHZF02-8C6DVDE&selectFields=videos")
    Single<TrailerResponse> loadTrailersMovies(@Query("id")int id);*/

    @GET("review?token=RNVF6PQ-FP34B0G-MVHZF02-8C6DVDE&limit=5")
    Single<ReviewResponse> loadReviewResponse(@Query("movieId") int movieId);
}
