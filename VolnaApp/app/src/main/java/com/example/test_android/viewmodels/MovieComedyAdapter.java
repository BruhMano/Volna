package com.example.test_android.viewmodels;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test_android.R;
import com.example.test_android.model.Category;
import com.example.test_android.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieComedyAdapter extends RecyclerView.Adapter<MovieComedyAdapter.MovieViewHolder> {
    private List<Movie> movies = new ArrayList<>();
    private MovieAllMoviesAdapter.onReachEndListener reachEndListener;
    private MovieAllMoviesAdapter.onMoviesListener onMoviesListener;

    public void setOnClickListener(MovieAllMoviesAdapter.onMoviesListener onMoviesListener) {
        this.onMoviesListener = onMoviesListener;
    }

    public void setOnReachEndListener(MovieAllMoviesAdapter.onReachEndListener onReachEndListener) {
        this.reachEndListener = onReachEndListener;
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_movie_vertical_item, parent, false);
        return new MovieViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);

        Glide.with(holder.itemView)
                .load(movie.getVertical_poster())
                .into(holder.imageViewHolder);
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
                .filter(c -> c.getCategories().contains(Category.comedy.name()))
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

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewHolder = itemView.findViewById(R.id.action_image_Film_item);
        }
    }
}
