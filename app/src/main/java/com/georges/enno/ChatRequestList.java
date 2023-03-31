package com.georges.enno;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.georges.enno.adapter.ChatRequestAdapter;
import com.georges.enno.ressources.ChatRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ChatRequestList extends AppCompatActivity {

    private final List<ChatRequest> chatRequests = new ArrayList<>(); // Define the list of chatRequests
    // Define a query to fetch the chatRequests from Firebase
    Query query = FirebaseDatabase.getInstance().getReference().child("chat_requests").orderByChild("userId2");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_request_list);

        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        RecyclerView recyclerView = findViewById(R.id.chat_request_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatRequestList.this));

        ChatRequestAdapter chatRequestAdapter = new ChatRequestAdapter(chatRequests); // create a new instance of ChatRequestAdapter
        recyclerView.setAdapter(chatRequestAdapter); // set the adapter for the RecyclerView

        // Read the posts from Firebase Realtime Database
        query.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatRequests.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChatRequest chatRequest = dataSnapshot.getValue(ChatRequest.class);
                    chatRequests.add(chatRequest);
                }
                // Reverse the order of the list
                Collections.reverse(chatRequests);

                chatRequestAdapter.notifyDataSetChanged(); // notify the adapter about the data changes
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("TAG", "onCancelled", error.toException());
            }
        });

        ImageView sendBackActivity = findViewById(R.id.send_back_ic);
        sendBackActivity.setOnClickListener(v -> {
            Intent sendBack = new Intent(ChatRequestList.this,MainActivity.class);
            startActivity(sendBack);
        });

    }

}