package com.georges.enno.ressources;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.georges.enno.MainActivity;
import com.georges.enno.PostCreation;
import com.georges.enno.R;
import com.georges.enno.adapter.CommentAdapter;
import com.georges.enno.adapter.PostAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CommentOpened extends AppCompatActivity {

    private final List<Comment> comments = new ArrayList<>(); // Define the list of comments
    TextView contentTextView;
    TextView authorTextView;
    TextView timeTextView;
    TextView likesTextView;
    TextView dislikesTextView;

    ImageView goBackButton;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_opened);

        mDatabase = FirebaseDatabase.getInstance().getReference("comments");
        DatabaseReference commentsRef = FirebaseDatabase.getInstance().getReference().child("comments");

        ImageView addNewComment = findViewById(R.id.comment_creation_send);
        addNewComment.setOnClickListener(v -> {
            // Get the current user's ID
            String authorId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

            // Generate a new ID for the comment
            String postId = commentsRef.push().getKey();

            // Get the comment text from the EditText
            EditText postEditText = findViewById(R.id.comment_edit_text);
            String postContent = postEditText.getText().toString();

            // Get the current timestamp in milliseconds
            long postTime = System.currentTimeMillis();

            // Create a new Post object with the generated ID, current user's ID, post text, and timestamp
            Post post = new Post(postId, authorId, postContent, postTime,0,0);

            // Save the post to the Realtime Database
            assert postId != null;
            commentsRef.child(postId).setValue(post);

            Toast.makeText(this, "Comment published !", Toast.LENGTH_SHORT).show();

        });

        RecyclerView recyclerView = findViewById(R.id.comment_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CommentAdapter commentAdapter = new CommentAdapter(comments); // create a new instance of CommentAdapter
        recyclerView.setAdapter(commentAdapter); // set the adapter for the RecyclerView

        // Read the posts from Firebase Realtime Database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                comments.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Comment comment = dataSnapshot.getValue(Comment.class);
                    comments.add(comment);
                }
                commentAdapter.notifyDataSetChanged(); // notify the adapter about the data changes

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("TAG", "onCancelled", error.toException());
            }

        });


        // Get references to the views in your CommentOpened activity layout
        contentTextView = findViewById(R.id.feed_card_comment_content);
        authorTextView = findViewById(R.id.feed_card_comment_id);
        timeTextView = findViewById(R.id.feed_card_comment_time);
        likesTextView = findViewById(R.id.post_card_comment_number_likes);
        dislikesTextView = findViewById(R.id.post_card_comment_number_dislikes);
        goBackButton = findViewById(R.id.go_back_comment);

        goBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("goToFragmentFeed", true);
            startActivity(intent);
        });

        // Retrieve the data passed from the previous activity (PostAdapter)
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        String author = intent.getStringExtra("author").substring(0, 8);
        long time = intent.getLongExtra("time", 0);
        int likes = intent.getIntExtra("likes", 0);
        int dislikes = intent.getIntExtra("dislikes", 0);

        // Display the retrieved data in CommentOpened activity layout
        contentTextView.setText(content);
        authorTextView.setText(author);
        timeTextView.setText(getFormattedTime(time));
        likesTextView.setText(String.format(String.valueOf(likes)));
        dislikesTextView.setText(String.format(String.valueOf(dislikes)));
    }


    private String getFormattedTime(long postTime) {
        // Format the time as desired
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


}


