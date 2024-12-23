package com.tyson.socialmedia.controller;


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



 //SocialMediaController class for CRUD functionality interfacing with service class
 @RestController
 public class CommentsController {

    //dependency injection to obtain an instance of the AccountService class
    @Autowired
    private AccountService accountService;

    //dependency injection to obtain an instance of the MessageService class
    @Autowired
    private MessageService messageService;

    //dependency injection to obtain an instance of the MessageService class
    @Autowired
    private CommentService commentService;



/* 

   //GET retrieveUserMessages to get all the messages from a single user and return them in a list format
    @GetMapping("/api/accounts/{accountId}/comments")
    public ResponseEntity<List<Comment>> retrieveUserComments(@PathVariable Integer accountId){

       // List<Comment> retrievedComments = new ArrayList<>();
        if(!accountService.accountExistsById(accountId))
        {
            return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
        }
        List<Comment> retrievedComments = commentService.retrieveUserComments(accountId);
        return new ResponseEntity<List<Comment>>(retrievedComments, HttpStatus.OK); 
    }


//POST createMessage method to create a new message, validates inputs and returns the created message
@PostMapping("/api/comments")
public ResponseEntity<Comment> createComment(@RequestBody @Valid Comment comment){

   // boolean exists = accountService.accountExistsById((Integer)comment.getPostedBy());
    if(!accountService.accountExistsById((Integer)comment.getCommentedBy())){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Comment createdComment = commentService.newComment(comment);
    return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
}
*/
@PostMapping("/api/auth/comments/{messageId}")
 public ResponseEntity<String> createComment(@RequestBody PostCommentDTO PostCommentDTO, @PathVariable Integer messageId){

    try{
        commentService.saveComment(PostCommentDTO, messageId);
     
     return ResponseEntity.status(HttpStatus.CREATED).body("Comment posted successfully");
    }
    catch(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to post comment" + e.getMessage());
    }
 }


//GET retrieveAllMessages method to retrieve all the messages that have been created from the database
@GetMapping("/api/auth/comments/{messageId}")
public ResponseEntity<List<CommentDTO>> retrieveMessageComment(@PathVariable Long messageId){

    List<CommentDTO> comments = commentService.getComments(messageId);
    return ResponseEntity.ok(comments);
}

//DELETE deleteMessage method to delete a message associated with a message ID  
@DeleteMapping("/api/comments/{comment_id}")
public ResponseEntity<Void> deleteComment(@PathVariable Integer comment_id){

   
    if(!commentService.doesCommentExist(comment_id)){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    commentService.deleteComment(comment_id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

//PATCH updateMessage method to recieve a message ID and an updated message text to update an existing message/  
@PatchMapping("/api/comments/{comment_id}")
public ResponseEntity<Void> updateComment(@PathVariable Integer comment_id, @RequestBody Map<String, String> update){
 
   // boolean exists = commentService.doesCommentExist(comment_id);
   if(!commentService.doesCommentExist(comment_id)){
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
    String updatedText = update.get("comment_text");
    commentService.updateComment(comment_id, updatedText);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

}
