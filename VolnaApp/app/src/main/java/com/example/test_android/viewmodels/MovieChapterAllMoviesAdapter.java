package com.example.test_android.viewmodels;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.test_android.R;
import com.example.test_android.model.Category;
import com.example.test_android.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieChapterAllMoviesAdapter extends RecyclerView.Adapter<MovieChapterAllMoviesAdapter.MovieViewHolder> {
    private List<Movie> movies = new ArrayList<>();
    private onReachEndListener reachEndListener;
    private onMoviesListener onMoviesListener;

    public void setOnClickListener(onMoviesListener onMoviesListener) {
        this.onMoviesListener = onMoviesListener;
    }

    public void setOnReachEndListener(onReachEndListener onReachEndListener) {
        this.reachEndListener = onReachEndListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_movie_chapter_vertical_item, parent, false);
        return new MovieViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);

        Glide.with(holder.itemView)
                .load(movie.getVertical_poster())
                .into(holder.imageViewHolder);

//        holder.textNameViewHolder.setText(movie.getTitle());

        StringBuilder info = new StringBuilder();
        info.append(movie.getYear()).append(" ").append("\u00B7").append(" ");
        info.append(movie.getGenre()).append("\u00B7").append(" ");
        info.append(movie.getAge()).append("+");

//        holder.textInfoViewHolder.setText(info.toString());

        if (position == movies.size() - 10 && reachEndListener != null) {
            reachEndListener.onReachEnd();
        }
        holder.itemView.setOnClickListener(v -> {
            if (onMoviesListener != null) {
                onMoviesListener.onClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies
                .stream()
                .collect(Collectors.toList());
        notifyDataSetChanged();
    }

    public void setMoviesComedy(List<Movie> movies) {
        this.movies = movies
                .stream()
                .filter(c -> c.getCategories().contains(Category.comedy.name()))
                .collect(Collectors.toList());
        notifyDataSetChanged();
    }
    public void setMoviesCrime(List<Movie> movies) {
        this.movies = movies
                .stream()
                .filter(c -> c.getCategories().contains(Category.crime.name()))
                .collect(Collectors.toList());
        notifyDataSetChanged();
    }
    public void setMoviesFantasy(List<Movie> movies) {
        this.movies = movies
                .stream()
                .filter(c -> c.getCategories().contains(Category.fantasy.name()))
                .collect(Collectors.toList());
        notifyDataSetChanged();
    }
    public void setMoviesHorror(List<Movie> movies) {
        this.movies = movies
                .stream()
                .filter(c -> c.getCategories().contains(Category.horror.name()))
                .collect(Collectors.toList());
        notifyDataSetChanged();
    }
    public void setMoviesHistory(List<Movie> movies) {
        this.movies = movies
                .stream()
                .filter(c -> c.getCategories().contains(Category.history.name()))
                .collect(Collectors.toList());
        notifyDataSetChanged();
    }
    public void setMoviesAnimation(List<Movie> movies) {
        this.movies = movies
                .stream()
                .filter(c -> c.getCategories().contains(Category.animation.name()))
                .collect(Collectors.toList());
        notifyDataSetChanged();
    }
    public void setMoviesDrama(List<Movie> movies) {
        this.movies = movies
                .stream()
                .filter(c -> c.getCategories().contains(Category.drama.name()))
                .collect(Collectors.toList());
        notifyDataSetChanged();
    }
    public void setMoviesAction(List<Movie> movies) {
        this.movies = movies
                .stream()
                .filter(c -> c.getCategories().contains(Category.action.name()))
                .collect(Collectors.toList());
        notifyDataSetChanged();
    }
    public void setMoviesAdventure(List<Movie> movies) {
        this.movies = movies
                .stream()
                .filter(c -> c.getCategories().contains(Category.adventure.name()))
                .collect(Collectors.toList());
        notifyDataSetChanged();
    }



    public interface onReachEndListener {
        void onReachEnd();
    }

    public interface onMoviesListener {
        void onClick(Movie movie);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewHolder;
//        private TextView textNameViewHolder;
//        private TextView textInfoViewHolder;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewHolder = itemView.findViewById(R.id.action_image_Film_item);
//            textNameViewHolder = itemView.findViewById(R.id.text_view_poster_film_name);
//            textInfoViewHolder = itemView.findViewById(R.id.text_view_poster_info);
        }
    }
}
