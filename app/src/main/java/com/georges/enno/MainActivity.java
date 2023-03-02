package com.georges.enno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.georges.enno.fragments.FragmentChat;
import com.georges.enno.fragments.FragmentFeed;
import com.georges.enno.fragments.FragmentNotifications;
import com.georges.enno.fragments.FragmentProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
