package com.georges.enno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class PostCreation extends AppCompatActivity {
    private EditText postEditText;

    TextView postButton;
    private DatabaseReference postsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_creation);

        postEditText = findViewById(R.id.post_content);
        postButton = findViewById(R.id.post_creation_send);

        // Get a reference to the posts node in the Realtime Database
        postsRef = FirebaseDatabase.getInstance().getReference().child("posts");

        postButton.setOnClickListener(view -> {
            // Get the current user's ID
            String authorId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

            // Generate a new ID for the post
            String postId = postsRef.push().getKey();

            // Get the post text from the EditText
            String postContent = postEditText.getText().toString();

            // Get the current timestamp in milliseconds
            long postTime = System.currentTimeMillis();

            // Create a new Post object with the generated ID, current user's ID, post text, and timestamp
            Post post = new Post(postId, authorId, postContent, postTime);

            // Save the post to the Realtime Database
            assert postId != null;
            postsRef.child(postId).setValue(post);

            // Finish the activity and go back to the main activity
            finish();
        });
    }
}