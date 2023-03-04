package com.georges.enno;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.georges.enno.adapter.MessageAdapter;
import com.georges.enno.fragments.FragmentChat;
import com.georges.enno.ressources.Message;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChatCreation extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private RecyclerView mMessagesList;
    private EditText mMessageEditText;
    private String mCurrentUserId;
    private MessageAdapter mAdapter;
    private List<Message> mMessages;

    TextView backActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_creation);

        backActivity = findViewById(R.id.chat_creation_back);
        backActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatCreation.this, FragmentChat.class);
                startActivity(intent);
            }
        });

        mCurrentUserId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mMessages = new ArrayList<>();

        mMessageEditText = findViewById(R.id.chat_edit_text);
        ImageView mSendButton = findViewById(R.id.chat_creation_send);
        mMessagesList = findViewById(R.id.chat_exchange_recyclerview);
        mAdapter = new MessageAdapter(mMessages);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mMessagesList.setLayoutManager(layoutManager);
        mMessagesList.setAdapter(mAdapter);

        mSendButton.setOnClickListener(view -> {
            String messageText = mMessageEditText.getText().toString().trim();
            if (!messageText.isEmpty()) {
                sendMessage(messageText);
            }
        });

        mDatabase.child("messages").addChildEventListener(new ChildEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, String previousChildName) {
                Message message = snapshot.getValue(Message.class);
                if (message != null) {
                    mMessages.add(message);
                    mAdapter.notifyDataSetChanged();
                    mMessagesList.smoothScrollToPosition(mMessages.size() - 1);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, String previousChildName) {}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, String previousChildName) {}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    private void sendMessage(String messageText) {
        Message message = new Message(messageText, mCurrentUserId, System.currentTimeMillis());
        String messageId = mDatabase.child("messages").push().getKey();
        Map<String, Object> messageValues = message.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/messages/" + messageId, messageValues);

        mDatabase.updateChildren(childUpdates).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Toast.makeText(ChatCreation.this, "Failed to send message", Toast.LENGTH_SHORT).show();
            }
        });
        mMessageEditText.setText("");
    }
}
