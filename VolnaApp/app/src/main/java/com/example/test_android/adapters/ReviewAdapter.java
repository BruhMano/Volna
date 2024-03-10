package com.example.test_android.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_android.models.reviews.Review;
import com.example.test_android.R;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private static final String TAG_REVIEW_POSITIVE = "Позитивный";
    private static final String TAG_REVIEW_NEGATIVE = "Негативный";
    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Log.d("onBindViewHolder", reviews.get(position).toString());
        Review review = reviews.get(position);
        holder.title.setText(review.getTitle());
        holder.type.setText(review.getType());
        holder.review.setText(review.getReview());

        int backgroundColor = getBackgroundReviewColor(review.getType(), holder.itemView.getContext());
        holder.title.setBackgroundColor(backgroundColor);
        holder.type.setBackgroundColor(backgroundColor);
        holder.review.setBackgroundColor(backgroundColor);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    private int getBackgroundReviewColor(String reviewType, Context context) {
        if (TAG_REVIEW_POSITIVE.equals(reviewType)) {
            return ContextCompat.getColor(context, android.R.color.holo_green_light);
        } else if (TAG_REVIEW_NEGATIVE.equals(reviewType)) {
            return ContextCompat.getColor(context, android.R.color.holo_red_light);
        } else {
            return ContextCompat.getColor(context, android.R.color.holo_orange_light);
        }
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView review;
        private TextView type;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleReview);
            review = itemView.findViewById(R.id.review);
            type = itemView.findViewById(R.id.typeReview);
        }
    }
}
