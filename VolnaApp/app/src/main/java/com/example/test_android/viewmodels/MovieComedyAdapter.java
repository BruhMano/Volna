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
import com.example.test_android.Movie;
import com.example.test_android.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieComedyAdapter extends RecyclerView.Adapter<MovieComedyAdapter.MovieViewHolder> {
    private List<Movie> movies = new ArrayList<>();
    private MovieHonorAdapter.onReachEndListener reachEndListener;
    private MovieHonorAdapter.onMoviesListener onMoviesListener;

    public void setOnClickListener(MovieHonorAdapter.onMoviesListener onMoviesListener) {
        this.onMoviesListener = onMoviesListener;
    }

    public void setOnReachEndListener(MovieHonorAdapter.onReachEndListener onReachEndListener) {
        this.reachEndListener = onReachEndListener;
    }

    @NonNull
    @Override
    public MovieComedyAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieComedyAdapter.MovieViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull MovieComedyAdapter.MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);

        Glide.with(holder.itemView)
                .load(movie.getHorizontal_poster())
                .into(holder.imageViewHolder);

        double rating = movie.getAge();
        /*int backgroundId;
        if (rating > 7) {
            backgroundId = R.drawable.circle_green;
        } else if (rating > 5) {
            backgroundId = R.drawable.circle_yellow;
        } else {
            backgroundId = R.drawable.circle_red;
        }*/

        /*Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(), backgroundId);
        holder.textViewHolder.setBackground(background);
        holder.textViewHolder.setText(String.format("%.1f", movie.getAge()));*/

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
                .filter(c -> c.isCategory_comedy())
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
        private TextView textViewHolder;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewHolder = itemView.findViewById(R.id.action_image_Film_item);
            textViewHolder = itemView.findViewById(R.id.text_view_poster_Film_item);
        }
    }
}
