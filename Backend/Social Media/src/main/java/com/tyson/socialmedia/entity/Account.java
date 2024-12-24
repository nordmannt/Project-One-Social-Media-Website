package com.tyson.socialmedia.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @NotBlank(message = "First name cannot be blank")
    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @NotBlank(message = "Username cannot be blank")
    @Column(name = "username", nullable = false, unique = true, length = 255)
    private String username;

    @Size(min = 4, message = "Password must be at least 4 characters")
    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "created_at_epoch", nullable = false)
    private Long createdAtEpoch;

    
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCreatedAtEpoch() {
        return createdAtEpoch;
    }

    public void setCreatedAtEpoch(Long createdAtEpoch) {
        this.createdAtEpoch = createdAtEpoch;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Account other = (Account) obj;
        return accountId != null && accountId.equals(other.accountId);
    }

    @Override
    public int hashCode() {
        return accountId != null ? accountId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", createdAtEpoch=" + createdAtEpoch +
                '}';
    }
}
