package com.example.test_android.api;

import static com.example.test_android.api.UrlConstant.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhpFactory {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build();

    public static final PhpService PHP_SERVICE = retrofit.create(PhpService.class);
}
