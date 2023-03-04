package com.georges.enno.ressources;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;

public class IncomeMessageService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        //TODO: send token to your server


    }
}
