package com.example.test_android.api;

import com.example.test_android.MovieResponse;

import io.reactivex.rxjava3.core.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface DownloadMovieService {
    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Url String url);
}
