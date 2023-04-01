package com.georges.enno.ressources;

public class ChatRequest {

    String receiverId ;
    String senderId ;
    String chatRoomId ;

    long chatTime;
    String status;
    public ChatRequest(String receiverId, String senderId, String chatRoomId, long chatTime, String status) {
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.chatRoomId = chatRoomId;
        this.chatTime = chatTime;
        this.status = status;
    }

    public ChatRequest() {
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderIdid() {
        return senderId;
    }

    public void setSenderIdid(String senderId) {
        this.senderId = senderId;
    }

    public String getChatRoom() {
        return chatRoomId;
    }

    public void setChatRoom(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public long getChatTime() {
        return chatTime;
    }

    public void setChatTime(long chatTime) {
        this.chatTime = chatTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
