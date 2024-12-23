package com.tyson.socialmedia.service;


import com.mysql.cj.log.ProfilerEvent;
import com.tyson.socialmedia.DTO.*;
import com.tyson.socialmedia.entity.Message;
import com.tyson.socialmedia.entity.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.metadata.PostgresTableMetaDataProvider;
import org.springframework.stereotype.Service;
import com.tyson.socialmedia.repository.MessageRepository;
import com.tyson.socialmedia.repository.ProfileRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.math.BigInteger;



import jakarta.transaction.Transactional;






@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MessageRepository messageRepository;
    
    private Message messages;
    
   // private ProfileDTO profileDTO;

   public ProfileDTO getProfileDTOByAccountId(Integer accountId) {
    // Fetch the profile based on the accountId
    Profile profile = profileRepository.findByAccount_AccountId(accountId);
    if (profile == null) {
        return null; // Handle no profile found case
    }

    // Fetch messages for the account using the repository
    List<Object[]> results = messageRepository.getProfileMessagesWithAccountDetails(accountId);

    // Map raw SQL result (Object[]) to a list of MessageDTOs
    List<MessageDTO> messages = results.stream()
        .map(row -> {
            MessageDTO dto = new MessageDTO();
            dto.setMessageId(((Number) row[0]).longValue()); // Assuming messageId is a Long
            dto.setMessageText((String) row[1]);            // Assuming messageText is a String
            dto.setTimePosted((Long) row[2]);               // Assuming timePosted is a Long
            dto.setFirstName((String) row[3]);              // Assuming firstName is a String
            dto.setLastName((String) row[4]);               // Assuming lastName is a String
            return dto;
        })
        .collect(Collectors.toList());

    // Return the ProfileDTO with profile and message details
    return new ProfileDTO(
        profile.getAccount().getFirstName(),
        profile.getAccount().getLastName(),
        profile.getBio(),
        profile.getAvatarUrl(),
        profile.getLocation(),
        messages // List of MessageDTOs
    );
}

public Profile saveProfile(Profile profile){
    return profileRepository.save(profile);
}

}
