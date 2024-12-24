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
 public class MessageController {

    //dependency injection to obtain an instance of the AccountService class
    @Autowired
    private AccountService accountService;

    //dependency injection to obtain an instance of the MessageService class
    @Autowired
    private MessageService messageService;





//POST createMessage method to create a new message, validates inputs and returns the created message
 @PostMapping("/api/auth/messages")
 public ResponseEntity<String> createMessage(@RequestBody PostDTO PostDTO){

    try{
        messageService.saveMessage(PostDTO);
     
     return ResponseEntity.status(HttpStatus.CREATED).body("Message posted successfully");
    }
    catch(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to post message" + e.getMessage());
    }
 }

 //GET retrieveAllMessages method to retrieve all the messages that have been created from the database
 @GetMapping("/api/auth/messages")
 public ResponseEntity<List<MessageDTO>> getAllMessages(){

     List<MessageDTO> messages = messageService.getMessages();
     return ResponseEntity.ok(messages); 
 }

/*
 @GetMapping("/api/auth/messages/{userId}")
 public ResponseEntity<List<MessageWithImagesDTO>> getMyNewsfeed(@PathVariable Integer userId){

     List<MessageWithImagesDTO> messages = messageService.getMessagesWithImages(userId);
     return ResponseEntity.ok(messages); 
 }
     */

 @GetMapping("/api/auth/messages/{userId}")
 public ResponseEntity<List<MessageDTO>> getMyNewsfeed(@PathVariable Integer userId){

     List<MessageDTO> messages = messageService.getMyMessages(userId);
     return ResponseEntity.ok(messages); 
 }



 @GetMapping("/api/auth/{userId}messages/")
 public ResponseEntity<List<MessageDTO>> getMyProfilefeed(@PathVariable Integer userId){

     List<MessageDTO> messages = messageService.getProfileMessages(userId);
     return ResponseEntity.ok(messages); 
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
 }