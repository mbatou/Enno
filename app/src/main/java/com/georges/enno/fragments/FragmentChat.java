package com.georges.enno.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.georges.enno.R;
import com.georges.enno.adapter.ChatRoomAdapter;
import com.georges.enno.adapter.PostAdapter;
import com.georges.enno.ressources.ChatRequest;
import com.georges.enno.ressources.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class FragmentChat extends Fragment {

    private final List<ChatRequest> chatRequests = new ArrayList<>(); // Define the list of chatRequests
    private DatabaseReference mDatabase;

    // Define a query to fetch the chatRequests from Firebase
    Query query = FirebaseDatabase.getInstance().getReference().child("chat_requests").orderByChild("userId2");


    public FragmentChat() {
        // Required empty public constructor
        }

@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    mDatabase = FirebaseDatabase.getInstance().getReference("chat_requests");

}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_chat, container, false);

    String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

    RecyclerView recyclerView = view.findViewById(R.id.chat_listview);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    ChatRoomAdapter chatRoomAdapter = new ChatRoomAdapter(chatRequests); // create a new instance of ChatRoomAdapter
    recyclerView.setAdapter(chatRoomAdapter); // set the adapter for the RecyclerView

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

            chatRoomAdapter.notifyDataSetChanged(); // notify the adapter about the data changes
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Log.e("TAG", "onCancelled", error.toException());
        }
    });

    return view;

    }

}