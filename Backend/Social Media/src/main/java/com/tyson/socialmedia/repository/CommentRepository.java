package com.tyson.socialmedia.repository;

import com.tyson.socialmedia.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    // Find all comments by a specific user
    List<Comment> findByCommentedBy(Integer commentedBy);

     @Query("SELECT c FROM Comment c JOIN Account a ON c.commentedBy = a.accountId")
    List<Comment> findCommentsWithUsername();

    @Query(value = """
    SELECT c.comment_id AS commentId, c.commented_by AS commentedBy, c.message_id AS messageId,  
    c.comment_text AS commentText, c.time_posted_epoch AS timePostedEpoch, a.first_name AS firstName, 
    a.last_name AS lastName FROM comments c
    JOIN account a ON c.commented_by = a.account_id WHERE c.message_id = :messageId
        """, nativeQuery = true)    
    List<Object[]>getCommentsWithAccountDetails(@Param("messageId") Long messageId); 

}