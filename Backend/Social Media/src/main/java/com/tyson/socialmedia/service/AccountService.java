
package com.tyson.socialmedia.service;

import com.tyson.socialmedia.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tyson.socialmedia.repository.AccountRepository;
import java.util.Optional;
import java.util.*;

//Creating AccountService class to interface between the controller and the message repository
@Service
public class AccountService {

    //dependency injection to obtain an instance of the AccountRepository class
    @Autowired
    private AccountRepository accountRepository;

    //register method to take an Account object and create a new record in the repository
    public Account register(Account account){
       
        return accountRepository.save(account);
    }

    public Account findByUsername(String username){
        return accountRepository.findByUsername(username);
    }


    public List<Account> searchUsers(String query) { // Fixed the return type syntax
        return accountRepository.searchUsers(query);
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
