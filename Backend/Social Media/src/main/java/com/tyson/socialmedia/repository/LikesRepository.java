package com.tyson.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tyson.socialmedia.entity.Like;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LikesRepository extends JpaRepository<Like, Integer> {
    boolean existsByLikedByAndMessageId(Integer likedBy, Integer messageId);

    boolean existsByLikedByAndCommentId(Integer likedBy, Integer commentId);
    
    @Transactional
    void deleteByLikedByAndMessageId(Integer likedBy, Integer messageId);
    @Transactional
    void deleteByLikedByAndCommentId(Integer likedBy, Integer commentId);
    Integer countByMessageId(Integer messageId);
    Integer countByCommentId(Integer commentId);
}
