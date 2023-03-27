package com.georges.enno.ressources;

public class ChatRequest {

    String userId1 ;
    String userId2 ;
    long requestTime;
    String status;
    public ChatRequest(String userId1, String userId2, long requestTime, String status) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.requestTime = requestTime;
        this.status = status;
    }

    public ChatRequest() {
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

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
