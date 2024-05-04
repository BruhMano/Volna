package com.example.test_android.api;

import static com.example.test_android.api.UrlConstant.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DownloadMovieFactory {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build();
    public static final DownloadMovieService DOWNLOAD_MOVIE_SERVICE = retrofit.create(DownloadMovieService.class);
}
