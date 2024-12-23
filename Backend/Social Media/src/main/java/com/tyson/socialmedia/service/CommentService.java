package com.tyson.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tyson.socialmedia.DTO.*;
import com.tyson.socialmedia.DTO.CommentDTO;
import com.tyson.socialmedia.DTO.MessageDTO;
import com.tyson.socialmedia.entity.Comment;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.math.BigInteger;

import com.tyson.socialmedia.repository.CommentRepository;

@Service
public class CommentService {
    
 @Autowired
    private CommentRepository commentRepository;


    public void saveComment(PostCommentDTO postCommentDTO, Integer messageId){
        Comment comment = new Comment();
        comment.setCommentedBy(postCommentDTO.getCommentedBy());
        comment.setMessageId(messageId);
        comment.setCommentText(postCommentDTO.getCommentText());
        comment.setTimePostedEpoch(Instant.now().getEpochSecond());
        commentRepository.save(comment);
    }

    public List<CommentDTO> getComments(Long messageId){
        List<Object[]> results = commentRepository.getCommentsWithAccountDetails(messageId);
        return results.stream().map(row-> {
            CommentDTO dto = new CommentDTO();
            dto.setCommentId(((Number) row[0]).longValue());
            dto.setCommentedBy(((Number) row[1]).longValue());
            dto.setMessageId(((Number) row[2]).longValue());
            dto.setCommentText(((String) row[3]));
            dto.setTimePosted(convertEpochToReadableDate((Number) row[4]));
            dto.setFirstName((String) row[5]);
            dto.setLastName((String) row[6]);
            return dto;
        }).collect(Collectors.toList());
    }

    public Comment newComment(Comment comment){

        return commentRepository.save(comment);
    }

    public void deleteComment(Integer comment_id){
              
        commentRepository.deleteById(comment_id);
    }

    public Comment updateComment(Integer comment_id, String updatedText){

        Comment comment = commentRepository.findById(comment_id)
        .orElseThrow(() -> new IllegalArgumentException("Comment not found with id: " + comment_id));
        
        comment.setCommentText(updatedText);
        
        return commentRepository.save(comment);
    }

    public List<Comment> retrieveCommentsWithUsername(){

       return commentRepository.findCommentsWithUsername();
    }

    public List<Comment> retrieveUserComments(Integer accountId){

     return commentRepository.findByCommentedBy(accountId);

    }

   public boolean doesCommentExist(Integer comment_id){

    return commentRepository.existsById(comment_id);
   }

   private String convertEpochToReadableDate(Number epoch) {
    if (epoch == null) {
        return "Invalid Date"; // Handle null epochs
    }
    long epochSeconds = epoch.longValue();
    Instant instant = Instant.ofEpochSecond(epochSeconds); 
    return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault()) 
            .format(instant); 
}
}
