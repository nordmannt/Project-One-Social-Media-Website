package com.tyson.socialmedia.repository;

import com.tyson.socialmedia.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {

    // Retrieve all friends for a specific user where the status is 'accepted'
    @Query("SELECT f FROM Friend f WHERE (f.friendA = :userId OR f.friendB = :userId) AND f.status = 'accepted'")
    List<Friend> findAcceptedFriendsByUserId(Integer userId);

    // Retrieve all pending friend requests sent to a user
    @Query("SELECT f FROM Friend f WHERE f.friendB = :userId AND f.status = 'pending'")
    List<Friend> findPendingRequestsByUserId(Integer userId);

    // Check if a friendship request already exists between two users
    @Query("SELECT COUNT(f) > 0 FROM Friend f WHERE (f.friendA = :userId AND f.friendB = :friendId) OR (f.friendA = :friendId AND f.friendB = :userId)")
    boolean friendshipExists(Integer userId, Integer friendId);
    
    // Delete a friendship by its ID
    void deleteById(Integer friendshipId);
}
