package com.georges.enno.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.georges.enno.ChatRequestList;
import com.georges.enno.R;
import com.georges.enno.adapter.ChatRequestAdapter;
import com.georges.enno.adapter.ChatRoomAdapter;
import com.georges.enno.ressources.ChatRequest;
import com.georges.enno.ressources.ChatRoom;
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

    private final List<ChatRoom> chatRooms = new ArrayList<>(); // Define the list of chatRequests
    private DatabaseReference mDatabase;
    CardView requestChatList;
    TextView textViewNumRequests;


    // Define a query to fetch the chatRequests from Firebase
    Query query = FirebaseDatabase.getInstance().getReference().child("chat_rooms").orderByChild("userId2");


    public FragmentChat() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference("chat_rooms");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

//    String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        RecyclerView recyclerView = view.findViewById(R.id.chat_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ChatRoomAdapter chatRoomAdapter = new ChatRoomAdapter(chatRooms); // create a new instance of ChatRequestAdapter
        recyclerView.setAdapter(chatRoomAdapter); // set the adapter for the RecyclerView

        // Read the posts from Firebase Realtime Database
        query.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatRooms.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChatRoom chatRoom = dataSnapshot.getValue(ChatRoom.class);
                    chatRooms.add(chatRoom);
                }
                // Reverse the order of the list
                Collections.reverse(chatRooms);

                chatRoomAdapter.notifyDataSetChanged(); // notify the adapter about the data changes
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("TAG", "onCancelled", error.toException());
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference chatRequestsRef = database.getReference("chat_rooms");

        textViewNumRequests = view.findViewById(R.id.number_chats_requests);

        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        chatRequestsRef.orderByChild("userId2").equalTo(userId).addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Count the number of chat requests the user has received
                int numRequests = (int) dataSnapshot.getChildrenCount();

                // Update your UI with the number of chat requests
                // For example, you could display the number of requests in a TextView
                textViewNumRequests.setText("( " + numRequests + " )");
                int color = ContextCompat.getColor(view.getContext(), R.color.orange_app);
                if (numRequests > 0){
                    textViewNumRequests.setTextColor(color);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });

        requestChatList = view.findViewById(R.id.requests_chat);
        requestChatList.setOnClickListener(v -> {
            Intent requestedChatList = new Intent(v.getContext(), ChatRequestList.class);
            startActivity(requestedChatList);
        });

        return view;

    }
}