package com.georges.enno.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.georges.enno.ChatCreation;
import com.georges.enno.R;
import com.georges.enno.ressources.Comment;
import com.georges.enno.ressources.CommentOpened;

import java.util.Collections;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private final List<Comment> comments;

    public CommentAdapter(List<Comment> comments) {
        // Sort the comments in descending order based on their post time
        Collections.sort(comments, (p1, p2) -> Long.compare(p2.getPostTime(), p1.getPostTime()));
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_card, parent, false);
        return new CommentAdapter.CommentViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentViewHolder holder, int position) {
        // Get the post at the current position
        Comment comment = comments.get(position);

        // Bind the data to the views
        holder.commentTextView.setText(comment.getContent());
        holder.authorTextView.setText(comment.getAuthorId().substring(0, 8));
        holder.timeTextView.setText(getFormattedTime(comment.getPostTime()));
        holder.likesTextView.setText(Integer.toString(comment.getLikes()));

        // Set click listeners for the like buttons
        holder.likeButton.setOnClickListener(view -> {
            comment.like();
            holder.likesTextView.setText(Integer.toString(comment.getLikes()));
        });

        holder.authorTextView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ChatCreation.class);
            intent.putExtra("author", comment.getAuthorId());
            v.getContext().startActivity(intent);
        });

        // Set click listener for the whole item view
        holder.itemView.setOnClickListener(v -> {
            // Create intent for the new activity
            Intent intent = new Intent(v.getContext(), CommentOpened.class);
            intent.putExtra("content", comment.getContent());
            intent.putExtra("author", comment.getAuthorId());
            intent.putExtra("time", comment.getPostTime());
            intent.putExtra("likes", comment.getLikes());

            // Start the new activity
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    private String getFormattedTime(long postTime) {
        long now = System.currentTimeMillis();
        long diff = now - postTime;

        if (diff < 5 * 60 * 1000) { // 5 minutes
            return "now";
        } else {
            long minutes = diff / (60 * 1000);
            long hours = minutes / 60;
            long days = hours / 24;

            if (days > 0) {
                return days + " days ago";
            } else if (hours > 0) {
                return hours + " hours ago";
            } else {
                return minutes + " minutes ago";
            }
        }
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {

        ImageView likeButton;
        TextView likesTextView;
        TextView commentTextView;
        TextView authorTextView;
        TextView timeTextView;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            commentTextView = itemView.findViewById(R.id.comment_card_content);
            authorTextView = itemView.findViewById(R.id.comment_card_id);
            timeTextView = itemView.findViewById(R.id.comment_card_time);
            likeButton = itemView.findViewById(R.id.like_comment);
            likesTextView = itemView.findViewById(R.id.comment_card_number_likes);
        }

    }
}



