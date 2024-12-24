

package com.tyson.socialmedia.repository;

import com.tyson.socialmedia.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


//MessageRepository class with 1 new method to findAllByPostedBy to retrieve a list of Message objects from a single account ID 
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    List<Message> findAllByPostedBy(Integer accountId);

    
@Query(value = """
    SELECT m.message_id AS messageId, m.message_text AS messageText, 
    m.time_posted_epoch AS timePostedEpoch, a.first_name AS firstName, 
    a.last_name AS lastName FROM message m
    JOIN account a ON m.posted_by = a.account_id 
        """, nativeQuery = true)    
    List<Object[]>getMessagesWithAccountDetails(); 

    @Query(value = """
    SELECT m.message_id AS messageId, m.posted_by AS posteBy, m.message_text AS messageText, 
    m.time_posted_epoch AS timePostedEpoch, a.first_name AS firstName, 
    a.last_name AS lastName FROM message m 
    JOIN account a ON m.posted_by = a.account_id WHERE m.posted_by IN (SELECT friendB FROM friends f WHERE friendA = :userId AND f.status = 'accepted' UNION SELECT friendA FROM friends f WHERE friendB = :userId AND f.status = 'accepted') OR m.posted_by = :userId ORDER BY m.time_posted_epoch DESC
        """, nativeQuery = true)    
    List<Object[]>getMyMessagesWithAccountDetails(@Param("userId") Integer userId); 

    @Query(value = """
    SELECT m.message_id AS messageId, m.message_text AS messageText, 
    m.time_posted_epoch AS timePostedEpoch, a.first_name AS firstName, 
    a.last_name AS lastName FROM message m 
    JOIN account a ON m.posted_by = a.account_id WHERE m.posted_by = :userId ORDER BY m.time_posted_epoch DESC 
        """, nativeQuery = true)    
    List<Object[]>getProfileMessagesWithAccountDetails(@Param("userId") Integer userId); 

    @Query(value = """
        SELECT 
            m.message_id AS messageId,
            m.posted_by AS postedBy,
            m.message_text AS messageText,
            m.time_posted_epoch AS timePosted,
            a.first_name AS firstName,
            a.last_name AS lastName,
            i.image_url AS imageUrl -- Include the single image URL for the message
        FROM message m
        JOIN account a ON m.posted_by = a.account_id
        LEFT JOIN images i ON m.message_id = i.message_id -- Join the images table to get the image URL
        WHERE 
            m.posted_by IN (
                SELECT friendB FROM friends f WHERE friendA = :userId AND f.status = 'accepted'
                UNION 
                SELECT friendA FROM friends f WHERE friendB = :userId AND f.status = 'accepted'
            )
            OR m.posted_by = :userId
        ORDER BY m.time_posted_epoch DESC
    """, nativeQuery = true)
    List<Object[]> getMyMessagesWithImages(@Param("userId") Integer userId);
    
   
}

