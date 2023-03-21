package com.georges.enno.ressources;

public class Comment {

    private String commentId;
    private String sendId;
    private String commentContent;
    private String getAgainPostId;
    private long commentPostTime;
    private int likes;

    public Comment() {
    }

    public Comment(String sendId,String commentId,String getAgainPostId,String commentContent,long commentPostTime,int likes) {
        this.sendId = sendId;
        this.commentId = commentId;
        this.getAgainPostId = getAgainPostId;
        this.commentContent = commentContent;
        this.commentPostTime = commentPostTime;
        this.likes = likes;

    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getGetAgainPostId() {
        return getAgainPostId;
    }

    public void setGetAgainPostId(String getAgainPostId) {
        this.getAgainPostId = getAgainPostId;
    }

    public long getCommentPostTime() {
        return commentPostTime;
    }

    public void setCommentPostTime(long commentPostTime) {
        this.commentPostTime = commentPostTime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void like() {
        likes++;
    }


}


