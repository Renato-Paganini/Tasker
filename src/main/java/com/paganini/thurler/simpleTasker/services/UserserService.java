package com.paganini.thurler.simpleTasker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paganini.thurler.simpleTasker.repositories.TaskRepository;
import com.paganini.thurler.simpleTasker.repositories.UserRepository;


@Service
public class UserserService { 
    //Autowired if the "constructor" of the interface
    @Autowired
    private UserRepository userRepository;

    private TaskRepository taskRepository;

    

    
}
