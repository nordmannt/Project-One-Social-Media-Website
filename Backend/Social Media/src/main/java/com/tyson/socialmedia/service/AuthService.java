package com.tyson.socialmedia.service;

import com.tyson.socialmedia.entity.Account;
import com.tyson.socialmedia.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public boolean validateUser(String username, String password){
        Account account = accountRepository.findByUsername(username);

        if (account ==null || !passwordEncoder.matches(password, account.getPassword())){
            return false;
        }
        return true;
    }

}
