package com.paganini.thurler.simpleTasker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.paganini.thurler.simpleTasker.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
  //JPA creates the methods to search the table
  @Transactional(readOnly = true)
  User findByUsername(String  username);
}
