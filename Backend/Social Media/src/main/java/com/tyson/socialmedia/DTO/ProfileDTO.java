package com.tyson.socialmedia.DTO;

import java.util.List;

public class ProfileDTO {
    private String firstName;
    private String lastName;
    private String bio; // Optional field for user bio or about info
    private String avatarUrl; // URL for profile picture
    private List<MessageDTO> posts; // List of posts associated with the user
    private String location;

    public ProfileDTO(String firstName, String lastName, String bio, String avatarUrl, String location, List<MessageDTO> posts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.avatarUrl = avatarUrl;
        this.location = location;
        this.posts = posts;
    }
    
    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<MessageDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<MessageDTO> posts) {
        this.posts = posts;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
