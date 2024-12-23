

package com.tyson.socialmedia.repository;
import com.tyson.socialmedia.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.*;

//AccountRepository class accepts and Account object and an Integer.  Contains 3 additional methods verify account by username, passord and return Account by username 
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    boolean existsByUsername(String username);
    boolean existsByPassword(String password);
   // Optional<Account> findByUsername(String username);
    Account findByUsername(String username);

   @Query("SELECT a FROM Account a WHERE " +
       "LOWER(a.firstName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
       "LOWER(a.lastName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
       "LOWER(a.username) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Account> searchUsers(@Param("query") String query);

}
