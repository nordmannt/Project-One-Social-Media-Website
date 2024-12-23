package com.tyson.socialmedia.repository;

import com.tyson.socialmedia.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findByAccount_AccountId(Integer accountId);

    
}
