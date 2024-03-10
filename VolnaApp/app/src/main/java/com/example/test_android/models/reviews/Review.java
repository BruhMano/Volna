package com.example.test_android.models.reviews;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable {
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private String type;
    @SerializedName("review")
    private String review;

    public Review(String title, String type, String review) {
        this.title = title;
        this.type = type;
        this.review = review;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getReview() {
        return review;
    }

    @Override
    public String toString() {
        return "Review{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}
