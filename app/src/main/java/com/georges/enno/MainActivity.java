package com.georges.enno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.georges.enno.fragments.FragmentChat;
import com.georges.enno.fragments.FragmentFeed;
import com.georges.enno.fragments.FragmentNotifications;
import com.georges.enno.fragments.FragmentProfile;
import com.georges.enno.ressources.Recharge;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView creditWallet = findViewById(R.id.credit_wallet);

        // Get the current user's ID
        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        // Check if the user has enough credits
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(userId).get().addOnSuccessListener(documentSnapshot -> {
            int credits = Objects.requireNonNull(documentSnapshot.getLong("credits")).intValue();
            creditWallet.setText(String.valueOf(credits));
        });

        creditWallet.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Recharge.class);
            startActivity(intent);
        });

        ImageView games = findViewById(R.id.games_icon);
        games.setOnClickListener(v -> {
            Intent games1 = new Intent(MainActivity.this, Games.class);
            startActivity(games1);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.main_navigation_view);

        performFragmentTransaction(new FragmentFeed());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int menuSelectedId = item.getItemId();

            Fragment fragment;

            if (menuSelectedId == R.id.feed) {
                fragment = new FragmentFeed();
            } else if (menuSelectedId == R.id.chat) {
                fragment = new FragmentChat();
            } else if (menuSelectedId == R.id.notifications) {
                fragment = new FragmentNotifications();
            } else {
                fragment = new FragmentProfile();
            }

            performFragmentTransaction(fragment);
            return true;
        });

    }

    private void performFragmentTransaction(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, fragment, "ok yes")
                .commit();
    }
}
