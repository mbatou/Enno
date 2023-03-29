package com.georges.enno.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.georges.enno.R;
import com.georges.enno.ressources.CommentOpened;
import com.georges.enno.ressources.Notification;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private final List<Notification> notifications;

    public NotificationAdapter(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationAdapter.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notifications_card, parent, false);
        return new NotificationAdapter.NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.NotificationViewHolder holder, int position) {
        // Get the post at the current position
        Notification notification = notifications.get(position);

        // Bind the data to the views
        holder.notificationTextView.setText(notification.getContentNotification());
        holder.authorTextView.setText(notification.getAuthorNotificationId().substring(0, 8));
        holder.timeTextView.setText(getFormattedTime(notification.getNotificationTime()));

        // Set click listener for the whole item view
        holder.itemView.setOnClickListener(v -> {
            // Create intent for the new activity
            Intent intent = new Intent(v.getContext(), CommentOpened.class);
//            intent.putExtra("content", notification.getContent());
//            intent.putExtra("author", notification.getAuthorId());
//            intent.putExtra("time", notification.getPostTime());
//            intent.putExtra("postId", notification.getId());

            // Start the new activity
//            v.getContext().startActivity(intent);
        });
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

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    static class NotificationViewHolder extends RecyclerView.ViewHolder{

            TextView notificationTextView, authorTextView;
            TextView timeTextView;



        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            notificationTextView =itemView.findViewById(R.id.notifications_card_content);
            authorTextView =itemView.findViewById(R.id.notifications_card_id);
            timeTextView =itemView.findViewById(R.id.notifications_card_time);
        }
    }
}
