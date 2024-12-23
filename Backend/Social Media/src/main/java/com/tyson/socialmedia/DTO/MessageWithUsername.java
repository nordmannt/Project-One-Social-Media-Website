package com.tyson.socialmedia.DTO;
import com.tyson.socialmedia.entity.*;


public class MessageWithUsername {
    
    private Message message;  // The actual message data (from the Message entity)
    private String username;  // The username of the person who posted the message

    // Constructor to initialize both fields
    public MessageWithUsername(Message message, String username) {
        this.message = message;
        this.username = username;
    }

    // Getter and Setter for Message
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    // Getter and Setter for Username
    public String getUsername() {
        return username;
    }

    
    public String getMessageText() {
        return message.getMessageText();
    }
   
     public Long getMessageTime() {
        return message.getTimePostedEpoch();
    }

    public void setUsername(String username) {
        this.username = username;
    }
}