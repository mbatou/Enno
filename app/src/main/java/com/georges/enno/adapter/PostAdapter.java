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
import com.georges.enno.ressources.CommentOpened;
import com.georges.enno.ressources.Post;
import com.georges.enno.R;

import java.util.Collections;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private final List<Post> posts;

    public PostAdapter(List<Post> posts) {
        // Sort the posts in descending order based on their post time
        Collections.sort(posts, (p1, p2) -> Long.compare(p2.getPostTime(), p1.getPostTime()));
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_card_post, parent, false);
        return new PostViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        // Get the post at the current position
        Post post = posts.get(position);

        // Bind the data to the views
        holder.postTextView.setText(post.getContent());
        holder.authorTextView.setText(post.getAuthorId().substring(0, 8));
        holder.timeTextView.setText(getFormattedTime(post.getPostTime()));
        holder.likesTextView.setText(Integer.toString(post.getLikes()));
        holder.dislikesTextView.setText(Integer.toString(post.getDislikes()));

        // Set click listeners for the like and dislike buttons
        holder.likeButton.setOnClickListener(view -> {
            post.like();
            holder.likesTextView.setText(Integer.toString(post.getLikes()));
        });

        holder.dislikeButton.setOnClickListener(view -> {
            post.dislike();
            holder.dislikesTextView.setText(Integer.toString(post.getDislikes()));
        });

        holder.authorTextView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(),ChatCreation.class);
            intent.putExtra("author",post.getAuthorId());
            v.getContext().startActivity(intent);
        });

        // Set click listener for the whole item view
        holder.itemView.setOnClickListener(v -> {
            // Create intent for the new activity
            Intent intent = new Intent(v.getContext(), CommentOpened.class);
            intent.putExtra("content", post.getContent());
            intent.putExtra("author", post.getAuthorId());
            intent.putExtra("time", post.getPostTime());
            intent.putExtra("likes", post.getLikes());
            intent.putExtra("dislikes", post.getDislikes());

            // Start the new activity
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
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

    static class PostViewHolder extends RecyclerView.ViewHolder {

        ImageView likeButton;
        ImageView dislikeButton;
        TextView likesTextView;
        TextView dislikesTextView;
        TextView postTextView;
        TextView authorTextView;
        TextView timeTextView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            postTextView = itemView.findViewById(R.id.feed_card_content);
            authorTextView = itemView.findViewById(R.id.feed_card_id);
            timeTextView = itemView.findViewById(R.id.feed_card_time);
            likeButton = itemView.findViewById(R.id.like_post);
            dislikeButton = itemView.findViewById(R.id.dislike_post);
            likesTextView = itemView.findViewById(R.id.post_card_number_likes);
            dislikesTextView = itemView.findViewById(R.id.post_card_number_dislikes);
        }

    }


}