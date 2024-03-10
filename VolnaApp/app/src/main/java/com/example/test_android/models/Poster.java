package com.example.test_android.models;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Poster implements Serializable {
    @SerializedName("previewUrl")
    private String previewUrl;

    public Poster() {
    }

    public Poster(String url) {
        this.previewUrl = url;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    @Override
    public String toString() {
        return "Poster{" +
                "previewUrl='" + previewUrl + '\'' +
                '}';
    }
}
