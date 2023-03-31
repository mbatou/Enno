package com.georges.enno.ressources;

public class ChatRoom {

    String userId1;
    String userId2;
    long chatTime;

    public ChatRoom(String userId1, String userId2, long chatTime) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.chatTime = chatTime;

    }
    public ChatRoom() {
    }

    public long getChatTime() {
        return chatTime;
    }

    public void setChatTime(long chatTime) {
        this.chatTime = chatTime;
    }

    public String getUserId1() {
        return userId1;
    }

    public void setUserId1(String userId1) {
        this.userId1 = userId1;
    }

    public String getUserId2() {
        return userId2;
    }

    public void setUserId2(String userId2) {
        this.userId2 = userId2;
    }
}
