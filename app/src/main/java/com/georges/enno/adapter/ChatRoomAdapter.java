package com.georges.enno.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.georges.enno.R;
import com.georges.enno.ressources.ChatRequest;
import java.util.List;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ChatRoomViewHolder> {

    private final List<ChatRequest> ChatRequests;

    public ChatRoomAdapter(List<ChatRequest> ChatRequests) {
        this.ChatRequests = ChatRequests;
    }

    @NonNull
    @Override
    public ChatRoomAdapter.ChatRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_card, parent, false);
        return new ChatRoomAdapter.ChatRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRoomAdapter.ChatRoomViewHolder holder, int position) {
        // Get the chatRequest at the current position
        ChatRequest chatRequest = ChatRequests.get(position);

        // Bind the data to the views
        holder.requestUserId.setText(chatRequest.getUserId1().substring(0, 8));
        holder.requestTime.setText(getFormattedTime(chatRequest.getRequestTime()));
        holder.requestStatus.setText(chatRequest.getStatus());

    }

    @Override
    public int getItemCount() {
        return ChatRequests.size();
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

    static class ChatRoomViewHolder extends RecyclerView.ViewHolder {

        TextView requestUserId;
        TextView requestTime;
        TextView requestStatus;

        public ChatRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            requestUserId = itemView.findViewById(R.id.chat_card_id);
            requestTime = itemView.findViewById(R.id.chat_card_time);
            requestStatus = itemView.findViewById(R.id.chat_card_content);
        }
    }
}
