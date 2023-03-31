package com.georges.enno.ressources;

public class Post {
    private String id;
    private String authorId;
    private String content;
    private long postTime;
    private int likes;
    private boolean isLiked;
    private int dislikes;
    private boolean isDisliked;

    public Post(String id, String authorId, String content, long postTime,  int likes,boolean isLiked, int dislikes, boolean isDisliked) {
        this.id = id;
        this.authorId = authorId;
        this.content = content;
        this.postTime = postTime;
        this.likes = likes;
        this.isLiked = isLiked; // Initialize the variable to false
        this.dislikes = dislikes;
        this.isDisliked = isDisliked; // Initialize the variable to false
    }

    public Post() {
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isDisliked() {
        return isDisliked;
    }

    public void setDisliked(boolean disliked) {
        isDisliked = disliked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPostTime() {
        return postTime;
    }

    public void setPostTime(long postTime) {
        this.postTime = postTime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void like() {
        if (!isLiked) {
            likes++;
            isLiked = true;}
    }

    public void dislike() {
        if (!isDisliked) {
            dislikes++;
            isDisliked = true;}
    }
}