package com.example.test_android.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "volna_films")
public class Movie implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    String title;
    @SerializedName("slug")
    String slug;
    @SerializedName("info")
    String info;
    @SerializedName("link")
    String link;
    @SerializedName("is_series")
    boolean is_series;
    @SerializedName("season")
    int season;
    @SerializedName("episode")
    int episode;
    @SerializedName("horizontal_poster")
    String horizontal_poster;
    @SerializedName("vertical_poster")
    String vertical_poster;
    @SerializedName("desc")
    String desc;
    @SerializedName("year")
    int year;
    @SerializedName("duration")
    int duration;
    @SerializedName("imdb")
    String imdb;
    @SerializedName("age")
    int age;

    @SerializedName("categories")
    String categories;

    public Movie(int id, String title, String slug, String info, String link, boolean is_series, int season, int episode,
                String horizontal_poster, String vertical_poster, String desc, int year, int duration, String imdb,
                int age, String categories) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.info = info;
        this.link = link;
        this.is_series = is_series;
        this.season = season;
        this.episode = episode;
        this.horizontal_poster = horizontal_poster;
        this.vertical_poster = vertical_poster;
        this.desc = desc;
        this.year = year;
        this.duration = duration;
        this.imdb = imdb;
        this.age = age;
        this.categories = categories;
    }
    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isIs_series() {
        return is_series;
    }

    public void setIs_series(boolean is_series) {
        this.is_series = is_series;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getHorizontal_poster() {
        return horizontal_poster;
    }

    public void setHorizontal_poster(String horizontal_poster) {
        this.horizontal_poster = horizontal_poster;
    }

    public String getVertical_poster() {
        return vertical_poster;
    }

    public void setVertical_poster(String vertical_poster) {
        this.vertical_poster = vertical_poster;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getGenre() {
        if (this.categories != null) {
            StringBuilder builder = new StringBuilder();
            String[] ss = this.categories.split(",(?!\\s)");
            for (String s : ss) {
                builder.append(Category.valueOf(s).getLocalName());
                builder.append(", ");
            }
            if (builder.length() > 0) {
                return builder.deleteCharAt(builder.length() - 2).toString();
            }
        }
        return "";
    }
}
