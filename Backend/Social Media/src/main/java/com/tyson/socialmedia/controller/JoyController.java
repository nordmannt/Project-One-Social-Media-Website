package com.tyson.socialmedia.controller;



import com.tyson.socialmedia.service.LikeService;

import com.tyson.socialmedia.entity.Like;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/auth/likes")
public class JoyController {
    @Autowired
    private LikeService likeService;

 
@GetMapping("/test")
public ResponseEntity<String> testEndpoint() {
    return ResponseEntity.ok("Controller is working!");
}

@GetMapping("/message/{messageId}/likeCount")
public ResponseEntity<Integer> getLikeMessage(@PathVariable Integer messageId) {
    Integer likeCount = likeService.getMessageLikeCount(messageId);
    return ResponseEntity.ok(likeCount);
}

@GetMapping("/comment/{commentId}/likeCount")
public ResponseEntity<Integer> getLikeComment(@PathVariable Integer commentId) {
    Integer likeCount = likeService.getCommentLikeCount(commentId);
    return ResponseEntity.ok(likeCount);
}


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

}
    