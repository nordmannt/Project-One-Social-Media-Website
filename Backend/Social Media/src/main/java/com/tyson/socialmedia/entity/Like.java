package com.tyson.socialmedia.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer likeId;

    @Column(nullable = false)
    private Integer likedBy;

    @Column
    private Integer messageId;

    @Column
    private Integer commentId;

    @Column(name = "time_posted_epoch", nullable = false)
    private Long timePostedEpoch;
    // Getters and Setters
    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Integer likedBy) {
        this.likedBy = likedBy;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Long getTimePostedEpoch() {
        return timePostedEpoch;
    }

    public void setTimePostedEpoch(Long timePostedEpoch) {
        this.timePostedEpoch = timePostedEpoch;
    }
}
