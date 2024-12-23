package com.tyson.socialmedia.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;



import com.tyson.socialmedia.entity.Like;
import com.tyson.socialmedia.repository.LikesRepository;

@Service
public class LikeService {

    @Autowired
    private LikesRepository likeRepository;

    public Integer getMessageLikeCount(Integer messageId){
        return likeRepository.countByMessageId(messageId);
    }
    public Integer getCommentLikeCount(Integer commentId){
        return likeRepository.countByCommentId(commentId);
    }


    public Like addLikeToMessage(Integer likedBy, Integer messageId) {
        Like like = new Like();
        like.setMessageId(messageId);
        like.setLikedBy(likedBy);
        like.setTimePostedEpoch(Instant.now().getEpochSecond()); // Set the current time
        return likeRepository.save(like);
    }

    public Like addLikeToComment(Integer likedBy, Integer commentId){

        Like like = new Like();
        like.setCommentId(commentId);
        like.setLikedBy(likedBy);
        like.setTimePostedEpoch(Instant.now().getEpochSecond()); // Set the current time
        return likeRepository.save(like);
    }
    @Transactional
    public void deleteLikeToMessage(Integer likedBy, Integer messageId){

       likeRepository.deleteByLikedByAndMessageId(likedBy, messageId);

    }
    @Transactional
    public void deleteLikeToComment(Integer likedBy, Integer comment_id){
        likeRepository.deleteByLikedByAndCommentId(likedBy, comment_id);
    }
   
    public boolean messageLikeExists(Integer likedBy, Integer messageId){

        return likeRepository.existsByLikedByAndMessageId(likedBy, messageId);
    }

    public boolean commentLikeExists(Integer likedBy, Integer comment_id){

        return likeRepository.existsByLikedByAndCommentId(likedBy, comment_id);
    }

    
}
