
package com.tyson.socialmedia.service;
import com.tyson.socialmedia.DTO.*;
import com.tyson.socialmedia.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import com.tyson.socialmedia.repository.MessageRepository;
import java.util.*;
import java.util.stream.Collectors;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.math.BigInteger;



import jakarta.transaction.Transactional;


//Creating MessageService class to interface between the controller and the message repository

@Service
public class MessageService {
    //dependency injection to obtain an instance of the MessageRepository class
    @Autowired
    private MessageRepository messageRepository;

    private String convertEpochToReadableDate(Number epoch) {
        if (epoch == null) {
            return "Invalid Date"; // Handle null epochs
        }
        long epochSeconds = epoch.longValue(); // Convert Number (Integer, Long, or BigInteger) to long
        Instant instant = Instant.ofEpochSecond(epochSeconds); // Create Instant from epoch seconds
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault()) // Use system's default timezone
                .format(instant); // Format Instant to a readable date
    }
    
    

 
    public void saveMessage(PostDTO postDTO){
        Message message = new Message();
        message.setPostedBy(postDTO.getPostedBy());
        message.setMessageText(postDTO.getMessageText());
        message.setTimePostedEpoch(Instant.now().getEpochSecond());
        messageRepository.save(message);


}

    public List<MessageDTO> getMessages(){
        List<Object[]> results = messageRepository.getMessagesWithAccountDetails();
        return results.stream().map(row-> {
            MessageDTO dto = new MessageDTO();
            dto.setMessageId(((Number) row[0]).longValue());
            dto.setMessageText(((String) row[1]));
            dto.setTimePosted(((Number) row[2]).longValue());
            dto.setFirstName((String) row[3]);
            dto.setLastName((String) row[4]);
            return dto;
        }).collect(Collectors.toList());
    }

        public List<MessageDTO> getMyMessages(Integer userId){
            List<Object[]> results = messageRepository.getMyMessagesWithAccountDetails(userId);
            return results.stream().map(row-> {
                MessageDTO dto = new MessageDTO();
                dto.setMessageId(((Number) row[0]).longValue());
                dto.setPostedBy(((Number) row[1]).longValue());
                dto.setMessageText(((String) row[2]));
                dto.setTimePosted(((Number) row[3]).longValue());
                dto.setFirstName((String) row[4]);
                dto.setLastName((String) row[5]);
                return dto;
            }).collect(Collectors.toList());
    }

    public List<MessageDTO> getProfileMessages(Integer userId){
        List<Object[]> results = messageRepository.getProfileMessagesWithAccountDetails(userId);
        return results.stream().map(row-> {
            MessageDTO dto = new MessageDTO();
            dto.setMessageId(((Number) row[0]).longValue());
            dto.setMessageText(((String) row[1]));
            dto.setTimePosted(((Number) row[2]).longValue());
            dto.setFirstName((String) row[3]);
            dto.setLastName((String) row[4]);
            return dto;
        }).collect(Collectors.toList());



}


/*
    public List<MessageWithUsername> retrieveMessagesWithUsername(){
        List<Object[]> result = messageRepository.findMessagesWithUsername();

        List<MessageWithUsername> messagesWithUsername = new ArrayList<>();

        for(Object[] row: result){
            Message message = (Message) row[0];
            String username = (String) row[1];
            messagesWithUsername.add(new MessageWithUsername(message,username));
        }
        return messagesWithUsername;
    }
*/
    //newMessage method to receive a message object and then save it to the repository to create a new message
    public Message newMessage(Message message){
        return messageRepository.save(message);
    }

    /*
    //retrieveMessage method to create a list of all messages from the repository and return to the calling method. 
    public List<Message> retrieveMessages(){
        return messageRepository.findAllMessagesOrderedByTime();
    }
*/
    //retrieveUserMessages method to receive an account ID and create a list of all messages created by that user account and return to the calling method in the controller
    public List<Message> retrieveUserMessages(Integer accountId){
       List<Message> messages = messageRepository.findAllByPostedBy(accountId);
       return messages;
    }
    
    //retrieveMessage method to receive a message ID and then look up the associated message to return to the calling method in the controller
    public Optional<Message> retrieveMessage(Integer messageId){
        return messageRepository.findById(messageId);
    }
    
    //deleteMessage method to receive a message ID and delete the associated message
    public int deleteMessage(Integer messageId){
        messageRepository.deleteById(messageId);
        return 1;
    }

    //updateMessage method to receive a message ID and the updated text, it looks up the message and saves the message with the new text added replacing the old text. 
    public void updateMessage(Integer messageId, Message message){
        Optional<Message> optionalMessage = retrieveMessage(messageId);
        Message newMessage = new Message();
        newMessage = optionalMessage.get();
        newMessage.setMessageText(message.getMessageText());
        messageRepository.save(newMessage);
    }

    //method to lookup if a message exists by the message ID
    public boolean doesMessageExist(Integer messageId){
    
       return messageRepository.existsById(messageId);
        
    }
}
