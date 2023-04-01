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

public class ChatRequestAdapter extends RecyclerView.Adapter<ChatRequestAdapter.ChatRequestViewHolder> {


    private final List<ChatRequest> chatRequests;

    public ChatRequestAdapter(List<ChatRequest> chatRequests) {
        this.chatRequests = chatRequests;
    }

    @NonNull
    @Override
    public ChatRequestAdapter.ChatRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_request_card, parent, false);
        return new ChatRequestAdapter.ChatRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRequestAdapter.ChatRequestViewHolder holder, int position) {
        // Get the chatRequest at the current position
        ChatRequest chatRequest = chatRequests.get(position);

        // Bind the data to the views
        holder.receiverId.setText(chatRequest.getReceiverId().substring(0, 8));
        holder.requestTime.setText(getFormattedTime(chatRequest.getChatTime()));

        // Approve or decline chatRequests
        holder.approveChat.setOnClickListener(v -> {
        });

        holder.declineChat.setOnClickListener(v -> {
        });

    }

    @Override
    public int getItemCount() {
        return chatRequests.size();
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

    static class ChatRequestViewHolder extends RecyclerView.ViewHolder {

        TextView receiverId;
        TextView requestTime;
        TextView approveChat;
        TextView declineChat;


        public ChatRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverId = itemView.findViewById(R.id.chat_card_id);
            requestTime = itemView.findViewById(R.id.chat_card_time);
            approveChat = itemView.findViewById(R.id.approve_requests);
            declineChat = itemView.findViewById(R.id.decline_requests);

        }

    }
}
