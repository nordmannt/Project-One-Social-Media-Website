package com.tyson.socialmedia.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendship_id") // Corrected column name
    private Integer friendshipId;

    @Column(name = "friendA", nullable = false)
    private Integer friendA;

    @Column(name = "friendB", nullable = false)
    private Integer friendB;

    @Column(nullable = false)
    private String status;

    @Column(name = "time_posted_epoch", nullable = false) // Corrected column name
    private Long timePosted;

    // Constructors
    public Friend() {
        this.timePosted = Instant.now().getEpochSecond(); // Default to current epoch time
    }

    public Friend(Integer friendA, Integer friendB, String status) {
        this.friendA = friendA;
        this.friendB = friendB;
        this.status = status;
        this.timePosted = Instant.now().getEpochSecond();
    }

    // Getters and Setters
    public Integer getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(Integer friendshipId) {
        this.friendshipId = friendshipId;
    }

    public Integer getFriendA() {
        return friendA;
    }

    public void setFriendA(Integer friendA) {
        this.friendA = friendA;
    }

    public Integer getFriendB() {
        return friendB;
    }

    public void setFriendB(Integer friendB) {
        this.friendB = friendB;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(Long timePosted) {
        this.timePosted = timePosted;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "friendshipId=" + friendshipId +
                ", friendA=" + friendA +
                ", friendB=" + friendB +
                ", status='" + status + '\'' +
                ", timePosted=" + timePosted +
                '}';
    }
}
