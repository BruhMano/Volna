package com.example.test_android.adapters;


import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test_android.R;
import com.example.test_android.models.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MovieViewHolder> {
    private List<Film> films = new ArrayList<>();
    private onReachEndListener reachEndListener;
    private onFilmsListener onFilmsListener;

    public void setOnClickListener(onFilmsListener onFilmsListener) {
        this.onFilmsListener = onFilmsListener;
    }

    public void setOnReachEndListener(onReachEndListener onReachEndListener) {
        this.reachEndListener = onReachEndListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item, parent, false);
        return new MovieViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Film film = films.get(position);

        Glide.with(holder.itemView)
                .load(film.getPoster().getPreviewUrl())
                .into(holder.imageViewHolder);

        double rating = film.getRating().getRating();
        int backgroundId;
        if (rating > 7) {
            backgroundId = R.drawable.circle_green;
        } else if (rating > 5) {
            backgroundId = R.drawable.circle_yellow;
        } else {
            backgroundId = R.drawable.circle_red;
        }
        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(), backgroundId);
        holder.textViewHolder.setBackground(background);
        holder.textViewHolder.setText(String.format("%.1f", film.getRating().getRating()));

        if (position == films.size() - 10 && reachEndListener != null) {
            reachEndListener.onReachEnd();
        }
        holder.itemView.setOnClickListener(v -> {
            if (onFilmsListener != null) {
                onFilmsListener.onClick(film);
            }
        });
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public void setFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    public interface onReachEndListener {
        void onReachEnd();
    }

    public interface onFilmsListener {
        void onClick(Film movie);
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
