package com.georges.enno;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Login extends AppCompatActivity {

    Button enterButton;
    String randomId, userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enterButton = findViewById(R.id.login_enter_the_space);
        enterButton.setOnClickListener(v -> FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {

                // User signed in anonymously
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                assert user != null;
                userId = user.getUid();

                // Generate a random ID
                randomId = UUID.randomUUID().toString().substring(0, 8);

                // Save the user ID and token to Firestore
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String, Object> userData = new HashMap<>();
                userData.put("token", task.getResult().toString());
                userData.put("id", randomId);
                userData.put("credits", 10);
                db.collection("users").document(userId).set(userData);

                // Save the user ID to SharedPreferences
                SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("userId", userId);
                editor.apply();
                SendToTheNextActivity();

            } else {

                // Handle sign-in failure
                Log.e("TAG", "signInAnonymously:failure", task.getException());
            }
        }));

    }

    private void SendToTheNextActivity() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
