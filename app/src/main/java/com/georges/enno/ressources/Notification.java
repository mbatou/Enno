package com.georges.enno.ressources;

public class Notification {

    private String NotificationId;
    private String authorNotificationId;
    private String contentNotification;
    private long notificationTime;

    public Notification(){

    }

    public String getNotificationId() {
        return NotificationId;
    }

    public void setNotificationId(String notificationId) {
        NotificationId = notificationId;
    }

    public String getAuthorNotificationId() {
        return authorNotificationId;
    }

    public void setAuthorNotificationId(String authorNotificationId) {
        this.authorNotificationId = authorNotificationId;
    }

    public String getContentNotification() {
        return contentNotification;
    }

    public void setContentNotification(String contentNotification) {
        this.contentNotification = contentNotification;
    }

    public long getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(long notificationTime) {
        this.notificationTime = notificationTime;
    }
}
