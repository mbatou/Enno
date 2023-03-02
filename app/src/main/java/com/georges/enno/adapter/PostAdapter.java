package com.georges.enno.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.georges.enno.Post;
import com.georges.enno.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private final List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_card_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        // Get the post at the current position
        Post post = posts.get(position);

        // Bind the data to the views
        holder.postTextView.setText(post.getContent());
        holder.authorTextView.setText(post.getAuthorId());
        holder.timeTextView.setText(getFormattedTime(post.getPostTime()));
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
        TextView postTextView;
        TextView authorTextView;
        TextView timeTextView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            postTextView = itemView.findViewById(R.id.feed_card_content);
            authorTextView = itemView.findViewById(R.id.feed_card_id);
            timeTextView = itemView.findViewById(R.id.feed_card_time);
        }
    }
}
