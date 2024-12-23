package com.tyson.socialmedia.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer messageId;

    @NotNull(message = "User ID cannot be null")
    @Column(name = "posted_by", nullable = false)
    private Long postedBy;

    @NotBlank(message = "Message cannot be blank")
    @Size(max = 255, message = "Message must be less than 256 characters")
    @Column(name = "message_text", nullable = false, length = 255)
    private String messageText;

    @NotNull(message = "Time posted cannot be null")
    @Column(name = "time_posted_epoch", nullable = false)
    private Long timePostedEpoch;

    // Default Constructor
    public Message() {
    }

    // Constructor without messageId (for creating new messages)
    public Message(Long postedBy, String messageText, Long timePostedEpoch) {
        this.postedBy = postedBy;
        this.messageText = messageText;
        this.timePostedEpoch = timePostedEpoch;
    }

    // Constructor with all fields (for retrieving messages)
    public Message(Integer messageId, Long postedBy, String messageText, Long timePostedEpoch) {
        this.messageId = messageId;
        this.postedBy = postedBy;
        this.messageText = messageText;
        this.timePostedEpoch = timePostedEpoch;
    }

    // Getters and Setters
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Long getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Long postedBy) {
        this.postedBy = postedBy;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Long getTimePostedEpoch() {
        return timePostedEpoch;
    }

    public void setTimePostedEpoch(Long timePostedEpoch) {
        this.timePostedEpoch = timePostedEpoch;
    }

    // Equals and HashCode for object comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Message other = (Message) obj;
        return messageId != null && messageId.equals(other.messageId);
    }

    @Override
    public int hashCode() {
        return messageId != null ? messageId.hashCode() : 0;
    }

    // toString for debugging
    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", postedBy=" + postedBy +
                ", messageText='" + messageText + '\'' +
                ", timePostedEpoch=" + timePostedEpoch +
                '}';
    }
}
