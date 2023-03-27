package com.georges.enno.ressources;

import java.util.HashMap;
import java.util.Map;

public class Message {

    private String text;
    private String userId;
    private long timestamp;

    public Message() {
    }

    public Message(String text, String userId, long timestamp) {
        this.text = text;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public String getUserId() {
        return userId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("text", text);
        result.put("userId", userId);
        result.put("timestamp", timestamp);
        return result;
    }
}

