package com.tyson.socialmedia.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer profileId;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account; // Foreign key linking to Account entity

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "avatar_url", length = 255)
    private String avatarUrl;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "created_at_epoch")
    private Long createdAtEpoch; // Changed column name to created_at_epoch

    @Column(name = "updated_at_epoch")
    private Long updatedAtEpoch; // Changed column name to updated_at_epoch

    // Getters and Setters
    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getCreatedAtEpoch() {
        return createdAtEpoch;
    }

    public void setCreatedAtEpoch(Long createdAtEpoch) {
        this.createdAtEpoch = createdAtEpoch;
    }

    public Long getUpdatedAtEpoch() {
        return updatedAtEpoch;
    }

    public void setUpdatedAtEpoch(Long updatedAtEpoch) {
        this.updatedAtEpoch = updatedAtEpoch;
    }
}
