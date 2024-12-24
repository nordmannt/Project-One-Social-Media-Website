package com.tyson.socialmedia.controller;


import com.tyson.socialmedia.service.FriendService;


import com.tyson.socialmedia.entity.Friend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping("/api/auth/friends")
public class FriendController {
private static final Logger logger = LoggerFactory.getLogger(FriendController.class);
@Autowired
private FriendService friendService;


 
@GetMapping("/test")
public ResponseEntity<String> testEndpoint() {
    return ResponseEntity.ok("Controller is working!");

}


@CrossOrigin(origins = "http://localhost:5173")
@PostMapping("/{userId}/request")
public ResponseEntity<Friend> friendRequest(
    @PathVariable Integer userId, // Extract userId from the URL
    @RequestBody Map<String, Integer> requestBody, // Extract receiverId from body
    @RequestHeader("Authorization") String token // JWT token from Authorization header
) {
    // Debug logging for Authorization header, userId, and receiverId
    logger.info("Authorization Header: {}", token);
    logger.info("UserId from Path: {}", userId);
    logger.info("ReceiverId from Request Body: {}", requestBody.get("receiverId"));

    // Debug logging using System.out for quick verification (optional)
    System.out.println("UserId from URL: " + userId);
    System.out.println("Authorization Header: " + token);
    System.out.println("ReceiverId from Body: " + requestBody.get("receiverId"));

    // Extract receiverId from the request body
    Integer receiverId = requestBody.get("receiverId");

    // Process the friend request using the service
    Friend request = friendService.requestFriend(userId, receiverId);

    // Return the created friend request with HTTP status 201 (Created)
    return new ResponseEntity<>(request, HttpStatus.CREATED);
}


@PutMapping("/{friendship_id}")
public ResponseEntity<String> updateFriendStatus(
    @PathVariable Integer friendship_id,
    @RequestParam String status
) {
    // Log inputs for debugging
    logger.info("Updating friendship with ID: {} to status: {}", friendship_id, status);

    // Validate status
    if (!status.equalsIgnoreCase("accepted") && !status.equalsIgnoreCase("rejected")) {
        logger.error("Invalid status: {}", status);
        return ResponseEntity.badRequest().body("Invalid status. Allowed values are 'accepted' or 'rejected'.");
    }

    // Update the status
    boolean updated = friendService.updateFriendStatus(friendship_id, status);
    if (updated) {
        return ResponseEntity.noContent().build(); // No content needed for successful updates
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Friendship not found.");
    }
}



@DeleteMapping("/{friendship_id}")
public ResponseEntity<Void> unFriend(@PathVariable Integer friendship_id) {
    
   
    friendService.removeFriend(friendship_id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}


@GetMapping("/{user_id}")
public ResponseEntity<List<Friend>> friendsLookup(@PathVariable Integer user_id) {
    
    List<Friend> friends = friendService.getFriends(user_id);
    
    if(friends.isEmpty()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(friends, HttpStatus.OK);
}

@GetMapping("/requests/{user_id}")
public ResponseEntity<List<Friend>> friendsPendingLookup(@PathVariable Integer user_id) {
    
    List<Friend> friends = friendService.getPendingFriends(user_id);
    if (friends.isEmpty()) {
        return ResponseEntity.noContent().build(); // No pending requests
    }
    return new ResponseEntity<>(friends, HttpStatus.OK);
}


}
