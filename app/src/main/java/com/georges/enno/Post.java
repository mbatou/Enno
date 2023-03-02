package com.georges.enno;

public class Post {
    private String id;
    private String authorId;
    private String content;
    private long postTime;

    public Post() {}

    public Post(String id, String authorId, String content, long postTime) {
        this.id = id;
        this.authorId = authorId;
        this.content = content;
        this.postTime = postTime;
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
}


