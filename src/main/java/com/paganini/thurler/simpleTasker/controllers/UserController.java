package com.paganini.thurler.simpleTasker.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paganini.thurler.simpleTasker.models.User;
import com.paganini.thurler.simpleTasker.models.User.CreateUser;
import com.paganini.thurler.simpleTasker.models.User.UpdateUser;
import com.paganini.thurler.simpleTasker.services.UserserService;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserserService userService;

    // localhost:8080/user/id
    //This searchs on the table
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id){
        User obj = this.userService.findByID(id);
        return ResponseEntity.ok().body(obj);
    }

    //This inserts on the table
    @PostMapping
    @Validated(CreateUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody User obj){
        this.userService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}") //it updates everthing
    @Validated(UpdateUser.class)
    public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Integer id){
        obj.setId(id);
        this.userService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
