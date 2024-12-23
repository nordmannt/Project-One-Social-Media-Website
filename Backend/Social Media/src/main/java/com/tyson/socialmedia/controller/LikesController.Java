package com.tyson.socialmedia.controller;

import com.tyson.socialmedia.service.FriendService;
import com.tyson.socialmedia.service.LikeService;
import com.tyson.socialmedia.entity.Friend;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/fun")
public class LikeController {
    
private FriendService friendService;

 
@GetMapping("/test")
public ResponseEntity<String> testEndpoint() {
    return ResponseEntity.ok("Controller is working!");
}

}

/*
import com.tyson.socialmedia.service.*;
import jakarta.validation.Valid;
import com.tyson.socialmedia.entity.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.core.io.Resource;
import java.nio.file.Files;
import org.springframework.http.HttpHeaders;
import com.tyson.socialmedia.DTO.*;


import com.tyson.socialmedia.service.LikeService;
import com.tyson.socialmedia.entity.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

 

 @RestController
 @RequestMapping("/api/like")
 public class LikesController {

  

     
    @Autowired
    private LikeService likeService;

    @GetMapping("/test")
public ResponseEntity<String> testEndpoint() {
    return ResponseEntity.ok("Controller is working!");
}

/*
    @PostMapping("/message/{messageId}")
    public ResponseEntity<Like> likeMessage(@PathVariable Integer messageId, @RequestParam Integer likedBy){
        Like newLike = likeService.addLikeToMessage(likedBy, messageId);
        return new ResponseEntity<>(newLike, HttpStatus.CREATED);
    }

    @PostMapping("/comment/{commentId}")
    public ResponseEntity<Like> likeComment(@PathVariable Integer commentId, @RequestParam Integer likedBy){
        Like newLike = likeService.addLikeToComment(likedBy, commentId);
        return new ResponseEntity<>(newLike, HttpStatus.CREATED);
    }
        
    @DeleteMapping("/message/{messageId}")
    public ResponseEntity<Void> unlikeMessage(@PathVariable Integer messageId, @RequestParam Integer likedBy){
        if(!likeService.messageLikeExists(likedBy, messageId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        likeService.deleteLikeToMessage(likedBy, messageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Void> unlikeComment(@PathVariable Integer commentId, @RequestParam Integer likedBy){
        if(!likeService.commentLikeExists(likedBy, commentId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        likeService.deleteLikeToComment(likedBy, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
*//*
}
    


*/
