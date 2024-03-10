package com.example.test_android.models.common;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Genre implements Serializable {
    @SerializedName("name")
    private String genre;

    public Genre(String name) {
        this.genre = name;
    }

    public Genre() {
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
