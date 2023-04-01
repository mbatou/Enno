package com.georges.enno.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.georges.enno.R;
import com.georges.enno.ressources.ChatRoom;


import java.util.List;


public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ChatRoomViewHolder> {

    private final List<ChatRoom> chatRooms;

    public ChatRoomAdapter(List<ChatRoom> chatRooms) {

        // Sort the chatRooms in descending order based on their post time
        this.chatRooms = chatRooms;
    }

    @NonNull
    @Override
    public ChatRoomAdapter.ChatRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_card, parent, false);
        return new ChatRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRoomAdapter.ChatRoomViewHolder holder, int position) {
        ChatRoom chatRoom = chatRooms.get(position);

        // Bind the data to the views
        holder.userId.setText(chatRoom.getUserId1().substring(0, 8));
        holder.chatTime.setText(getFormattedTime(chatRoom.getChatTime()));

    }

    @Override
    public int getItemCount() {
        return chatRooms.size();
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

        TextView userId;
        TextView chatTime;


        public ChatRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.chat_card_id);
            chatTime = itemView.findViewById(R.id.chat_card_time);

        }

    }
}
