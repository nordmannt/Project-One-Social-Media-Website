package com.tyson.socialmedia.DTO;

public class AccountDTO {
    private Integer accountId;
    private String firstName;
    private String lastName;
    private String avatarUrl;

    // Constructor
    public AccountDTO(Integer accountId, String firstName, String lastName, String avatarUrl) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
    }

    // Getters and Setters
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl= avatarUrl;
    }
}
