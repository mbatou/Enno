package com.georges.enno;

public class Post {
    private String id;
    private String authorId;
    private String content;
    private long postTime;
    private int likes;
    private int dislikes;

    public Post(String postId, String authorId, String postContent, long postTime) {

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

    public Post() {
    }

    public Post(String id, String authorId, String content, long postTime,int likes, int dislikes) {
        this.id = id;
        this.authorId = authorId;
        this.content = content;
        this.postTime = postTime;
        this.likes = likes;
        this.dislikes = dislikes;
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

    public void like() {
    }
    public void dislike() {
    }
}


