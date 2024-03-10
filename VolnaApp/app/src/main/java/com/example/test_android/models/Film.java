package com.example.test_android.models;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.test_android.models.common.Genre;
import com.example.test_android.models.common.ItemType;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity(tableName = "volna_films")
public class Film implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("year")
    private int year;
   /* @Embedded
    @SerializedName("genre")
    private Genre genre;*/
    @SerializedName("type")
    private ItemType type;
    @Embedded
    @SerializedName("poster")
    private Poster poster;
    @Embedded
    @SerializedName("rating")
    private Rating rating;

    public Film(int id, String name, String description, int year, Poster poster, Rating rating,
                ItemType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.poster = poster;
        this.rating = rating;
        //this.genre = genres;
        this.type = type;
    }
    public Film() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Poster getPoster() {
        return poster;
    }

    public void setPoster(Poster poster) {
        this.poster = poster;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", poster=" + poster +
                ", rating=" + rating +
                '}';
    }
}
