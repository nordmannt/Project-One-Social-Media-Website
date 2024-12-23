/*/
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
 public class SocialMediaController {

    //dependency injection to obtain an instance of the AccountService class
    @Autowired
    private AccountService accountService;

    //dependency injection to obtain an instance of the MessageService class
    @Autowired
    private MessageService messageService;

    @GetMapping("/home")
    public ResponseEntity<Resource> rootEndpoint() {
        Resource resource = new ClassPathResource("static/home.html");

        //String content = new String(Files.readAllBytes(resource.getFile().toPath()));

        return ResponseEntity
        .status(HttpStatus.OK)
        .header(HttpHeaders.CONTENT_TYPE, "text/html")
        .body(resource);
    }
 @GetMapping("/api/login")
    public ResponseEntity<Resource> loginEndpoint() {
        Resource resource = new ClassPathResource("static/login.html");

       

        return ResponseEntity
        .status(HttpStatus.OK)
        .header(HttpHeaders.CONTENT_TYPE, "text/html")
        .body(resource);
    }

    @GetMapping("/api/register")
    public ResponseEntity<Resource> registerEndpoint() {
        Resource resource = new ClassPathResource("static/register.html");

        //String content = new String(Files.readAllBytes(resource.getFile().toPath()));

        return ResponseEntity
        .status(HttpStatus.OK)
        .header(HttpHeaders.CONTENT_TYPE, "text/html")
        .body(resource);
    }


   
   
    //POST registerUser method to register user accounts, validates account information  
    @PostMapping("/api/register")
    public ResponseEntity<Resource> registerUser(@RequestParam("username") String username, @RequestParam("password") String password){
           // String username = account.getUsername();
            boolean exists = accountService.accountExists(username);
            if(exists){
                Resource resource = new ClassPathResource("static/login.html");
                return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, "text/html")
                .body(resource);
            }
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(password);
            Account createdAccount = accountService.register(account);
            Resource resource = new ClassPathResource("static/successfulRegistration.html");
            return ResponseEntity
            .status(HttpStatus.OK)
            .header(HttpHeaders.CONTENT_TYPE, "text/html")
            .body(resource);
        
       
        }

        @PostMapping("/api/login")
    public ResponseEntity<Resource> loginUser(@RequestParam("username") String username, @RequestParam("password") String password){
           // String username = account.getUsername();
            boolean exists = accountService.accountExists(username);
            if(!exists){
                Resource resource = new ClassPathResource("static/invalidLogin.html");
                return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, "text/html")
                .body(resource);
            }
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(password);
            if(accountService.login(account)){
                Resource resource = new ClassPathResource("static/successfulLogin.html");
                return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, "text/html")
                .body(resource);
            
            }
            Resource resource = new ClassPathResource("static/invalidLogin.html");
            return ResponseEntity
            .status(HttpStatus.OK)
            .header(HttpHeaders.CONTENT_TYPE, "text/html")
            .body(resource);
        }
   /* Maybe don't need to use
        @GetMapping("/api/messages")
        public ResponseEntity<Resource> messageEndpoint() {
            Resource resource = new ClassPathResource("static/messages.html");
        
    
            return ResponseEntity
            .status(HttpStatus.OK)
            .header(HttpHeaders.CONTENT_TYPE, "text/html")
            .body(resource);
        }
*/

/*
    //POST createMessage method to create a new message, validates inputs and returns the created message
    @PostMapping("/api/messages")
    public ResponseEntity<Message> createMessage(@RequestBody @Valid Message message){

        boolean exists = accountService.accountExistsById((Integer)message.getPostedBy());
        if(!exists){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Message createdMessage = messageService.newMessage(message);
        return new ResponseEntity<>(createdMessage, HttpStatus.OK);
    }

    //GET retrieveAllMessages method to retrieve all the messages that have been created from the database
    @GetMapping("/api/messages")
    public ResponseEntity<List<MessageWithUsername>> retrieveMessagesWUsername(){

        List<MessageWithUsername> messages = new ArrayList<>();
        messages = messageService.retrieveMessagesWithUsername();
        return new ResponseEntity<List<MessageWithUsername>>(messages, HttpStatus.OK); 
    }

    //GET retrieveMessage method to lookup a message from the associated message ID
    @GetMapping("/api/messages/{messageId}")
    public ResponseEntity<Optional<Message>> retrieveMessage(@PathVariable Integer messageId){
     Optional<Message> retrievedMessage = messageService.retrieveMessage(messageId); 
        boolean exists = messageService.doesMessageExist(messageId);
        if(!exists){
            return new ResponseEntity<Optional<Message>>(HttpStatus.OK);
        }
     return new ResponseEntity<Optional<Message>>(retrievedMessage, HttpStatus.OK);
    }

    //DELETE deleteMessage method to delete a message associated with a message ID  
    @DeleteMapping("/api/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable Integer messageId){
   
        Integer value = 1;
        boolean exists = messageService.doesMessageExist(messageId);
        if(!exists){
            return new ResponseEntity<Integer>(HttpStatus.OK);
        }
        value = messageService.deleteMessage(messageId);
        return new ResponseEntity<Integer>(value, HttpStatus.OK);
    }

    //PATCH updateMessage method to recieve a message ID and an updated message text to update an existing message/  
    @PatchMapping("/api/messages/{messageId}")
    public ResponseEntity<Integer> updateMessage(@PathVariable Integer messageId, @RequestBody @Valid Message message){
     
        boolean exists = messageService.doesMessageExist(messageId);
       if(!exists){
            return new ResponseEntity<Integer>( HttpStatus.BAD_REQUEST);
        }
        messageService.updateMessage(messageId, message);
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    //GET retrieveUserMessages to get all the messages from a single user and return them in a list format
    @GetMapping("/api/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> retrieveUserMessages(@PathVariable Integer accountId){

        List<Message> retrievedMessages = new ArrayList<>();
        boolean exists = accountService.accountExistsById(accountId);
        if(!exists){
            return new ResponseEntity<List<Message>>(HttpStatus.NOT_FOUND);
        }
        retrievedMessages = messageService.retrieveUserMessages(accountId);
        return new ResponseEntity<List<Message>>(retrievedMessages, HttpStatus.OK); 
    }
}

//POST createMessage method to create a new message, validates inputs and returns the created message
@PostMapping("/api/comments")
public ResponseEntity<Comment> createComment(@RequestBody @Valid Comment comment){

    boolean exists = accountService.accountExistsById((Integer)comment.getPostedBy());
    if(!exists){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Comment createdComment = commentService.newComment(comment);
    return new ResponseEntity<>(createdComment, HttpStatus.OK);
}

//GET retrieveAllMessages method to retrieve all the messages that have been created from the database
@GetMapping("/api/comments")
public ResponseEntity<List<MessageWithUsername>> retrieveMessagesWUsername(){

    List<MessageWithUsername> messages = new ArrayList<>();
    messages = messageService.retrieveMessagesWithUsername();
    return new ResponseEntity<List<MessageWithUsername>>(messages, HttpStatus.OK); 
}

//DELETE deleteMessage method to delete a message associated with a message ID  
@DeleteMapping("/api/comments/{comment_id}")
public ResponseEntity<Void> deleteComment(@PathVariable Integer comment_id){

   // Integer value = 1;
    boolean exists = commentService.doesCommentExist(comment_id);
    if(!exists){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //value = messageService.deleteMessage(messageId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

//PATCH updateMessage method to recieve a message ID and an updated message text to update an existing message/  
@PatchMapping("/api/comments/{comment_id}")
public ResponseEntity<Void> updateComment(@PathVariable Integer comment_id, @RequestBody Map<String, String> update){
 
    boolean exists = commentService.doesCommentExist(comment_id);
   if(!exists){
        return new ResponseEntity<Integer>( HttpStatus.BAD_REQUEST);
    }
    String updatedText = update.get("comment_text");
    commentService.updateComment(comment_id, updatedText);
    return new ResponseEntity<HttpStatus.NO_CONTENT);
}
*/