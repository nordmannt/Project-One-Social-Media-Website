package com.tyson.socialmedia.DTO;

import java.sql.Timestamp;

public class CommentsWithUsername {

    private Integer commentId;
    private Integer messageId;
    private String commentText;
    private String username;
    private Timestamp timePosted;

    // Constructor
    public CommentsWithUsername(Integer commentId, Integer messageId, String commentText, String username, Timestamp timePosted) {
        this.commentId = commentId;
        this.messageId = messageId;
        this.commentText = commentText;
        this.username = username;
        this.timePosted = timePosted;
    }

    // Getters and Setters
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(Timestamp timePosted) {
        this.timePosted = timePosted;
    }
}
