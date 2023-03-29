package com.georges.enno.ressources;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.georges.enno.MainActivity;
import com.georges.enno.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Recharge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        // Get the current user's ID
        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        TextView creditBalance = findViewById(R.id.credit_balance);

        // Check if the user has enough credits
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(userId).get().addOnSuccessListener(documentSnapshot -> {
            int credits = Objects.requireNonNull(documentSnapshot.getLong("credits")).intValue();
            creditBalance.setText(String.valueOf(credits));
        });


        ImageView sendBackButton = findViewById(R.id.send_back_ic);
        Button addCredits10 = findViewById(R.id.btn_recharge);
        Button addCredits20 = findViewById(R.id.btn_transfer);

        addCredits10.setOnClickListener(v -> {
            // Check the user credits
            db.collection("users").document(userId).get().addOnSuccessListener(documentSnapshot -> {
                int credits = Objects.requireNonNull(documentSnapshot.getLong("credits")).intValue();

                // Add 10 credits from the user's account
                db.collection("users").document(userId).update("credits", credits + 10);
                Toast.makeText(this, "10 credits have been added to your balance", Toast.LENGTH_SHORT).show();
            });
        });

        addCredits20.setOnClickListener(v -> {
            // Check the user credits
            db.collection("users").document(userId).get().addOnSuccessListener(documentSnapshot -> {
                int credits = Objects.requireNonNull(documentSnapshot.getLong("credits")).intValue();

                // Add 20 credits from the user's account
                db.collection("users").document(userId).update("credits", credits + 20);
                Toast.makeText(this, "20 credits have been added to your balance", Toast.LENGTH_SHORT).show();
            });
        });

        sendBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(Recharge.this, MainActivity.class);
            startActivity(intent);
        });
    }
}