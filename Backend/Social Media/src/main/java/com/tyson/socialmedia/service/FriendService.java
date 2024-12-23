package com.tyson.socialmedia.service;


import com.tyson.socialmedia.entity.Friend;
import com.tyson.socialmedia.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class FriendService {
    
    @Autowired
    private FriendRepository friendRepository;



    

    public Friend requestFriend(Integer user_id, Integer friend_id){

        Friend friendRequest = new Friend();
        friendRequest.setFriendA(user_id);
        friendRequest.setFriendB(friend_id);
        friendRequest.setStatus("Pending");
        friendRequest.setTimePosted(Instant.now().getEpochSecond());

        return friendRepository.save(friendRequest);
    }





    public boolean updateFriendStatus(Integer friendship_id, String status){
        Optional<Friend> friendship = friendRepository.findById(friendship_id);

       if(friendship.isPresent()){
        Friend friend = friendship.get();
        friend.setStatus(status.toLowerCase());
        friendRepository.save(friend);
       // logger.info("Updated friendship ID {} to status {}", friendshipId, status);
        return true;
       }
       //logger.warn("Friendship ID {} not found", friendshipId);
       return false;
    }

    public void removeFriend(Integer friendship_id){

        friendRepository.deleteById(friendship_id);
    }

    public List<Friend> getFriends(Integer user_id){

      

        return friendRepository.findAcceptedFriendsByUserId(user_id);
    }

    public List<Friend> getPendingFriends(Integer user_id) {
        return friendRepository.findPendingRequestsByUserId(user_id);
}

}