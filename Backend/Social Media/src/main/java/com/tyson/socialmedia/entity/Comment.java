package com.tyson.socialmedia.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(nullable = false)
    private Integer commentedBy;

    @Column(nullable = false)
    private Integer messageId;

    @Column(nullable = false, length = 500)
    private String commentText;

    
    @Column(name = "time_posted_epoch", nullable = false)
    private Long timePostedEpoch;
    // Getters and Setters
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(Integer commentedBy) {
        this.commentedBy = commentedBy;
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

    public Long getTimePostedEpoch() {
        return timePostedEpoch;
    }

    public void setTimePostedEpoch(Long timePostedEpoch) {
        this.timePostedEpoch = timePostedEpoch;
    }
}
