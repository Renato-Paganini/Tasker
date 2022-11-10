package com.paganini.thurler.simpleTasker.services;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paganini.thurler.simpleTasker.models.User;
import com.paganini.thurler.simpleTasker.repositories.UserRepository;


@Service
public class UserserService { 
    //Autowired if the "constructor" of the interface
    @Autowired
    private UserRepository userRepository;

  

    public User findByID(Integer id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
            "User not found: id:"+id+" type: " + User.class.getName()
        ));
    }

    //This is to save on the data base
    //C< RUD
    @Transactional
    public User create(User obj){
        //If someone sends an object with id, let's ignore it
        obj.setId(null);
        obj= this.userRepository.save(obj);
        return obj;

    }

    //U
    @Transactional
    public User update(User obj){
        User newObj = findByID(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }


    public void delete(Integer id){
        findByID(id);
        try{
            this.userRepository.deleteById(id);
        }catch(Exception e){
            throw new RuntimeException("User has relations");
        }
    }
}
