package com.georges.enno.ressources;

public class Comment {

    private String id;
    private String authorId;
    private String content;
    private long postTime;
    private int likes;

    public Comment(String id, String authorId, String content, long postTime, int likes) {
        this.id = id;
        this.authorId = authorId;
        this.content = content;
        this.postTime = postTime;
        this.likes = likes;
    }
    public Comment() {
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

    public void like() {
        likes++;
    }


}


