package com.georges.enno.ressources;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.georges.enno.MainActivity;
import com.georges.enno.R;
import com.georges.enno.fragments.FragmentFeed;

import java.util.Objects;


public class CommentOpened extends AppCompatActivity {
    TextView contentTextView;
    TextView authorTextView;
    TextView timeTextView;
    TextView likesTextView;
    TextView dislikesTextView;

    ImageView goBackButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_opened);

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

        // Retrieve the data passed from the previous activity (i.e., PostAdapter)
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

