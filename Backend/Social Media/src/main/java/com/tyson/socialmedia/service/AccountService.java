
package com.tyson.socialmedia.service;

import com.tyson.socialmedia.DTO.AccountDTO;
import com.tyson.socialmedia.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tyson.socialmedia.repository.AccountRepository;
import com.tyson.socialmedia.repository.ProfileRepository;

import java.util.*;
import java.util.stream.Collectors;

//Creating AccountService class to interface between the controller and the message repository
@Service
public class AccountService {

    //dependency injection to obtain an instance of the AccountRepository class
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    //register method to take an Account object and create a new record in the repository
    public Account register(Account account){
       
        return accountRepository.save(account);
    }

    public Account findByUsername(String username){
        return accountRepository.findByUsername(username);
    }





   public List<AccountDTO> searchUsers(String query) {
    // Fetch accounts based on query
    List<Account> accounts = accountRepository.searchUsers(query);

    // Map Account to AccountDTO, including Profile data
    return accounts.stream()
            .map(account -> {
                // Fetch profile for the account
                Profile profile = profileRepository.findByAccount_AccountId(account.getAccountId());
                
                // Return a new AccountDTO
                return new AccountDTO(
                        account.getAccountId(),
                        account.getFirstName(),
                        account.getLastName(),
                        profile != null ? profile.getAvatarUrl() : null // Add profile picture if available
                );
            })
            .collect(Collectors.toList()); // Collect into a list
}

    








    //login method to check that the username and password are valid for the account
   // public boolean login(Account account){
      
    //    Optional<Account> accountCheck = accountRepository.findByUsername(account.getUsername());
      
     //   if(accountCheck.isPresent() && accountCheck.get().getPassword().equals(account.getPassword())){
      //  return true;
      //  }
     //   return false;
   // }

    //accountByUsername method to lookup an Account by the username and return it to the calling method. 
   // public Optional<Account> accountByUsername(Account account){
    //    return accountRepository.findByUsername(account.getUsername());
    //}

    //accountExistsById method to return true if an account exists by the account ID provided
    public boolean accountExistsById(Integer accountId){

        return accountRepository.existsById(accountId);

    }

    //accountExists method to check if an account exists by a username and returns the boolean value
    public boolean accountExists(String username){

        return accountRepository.existsByUsername(username);

    }

    
}
