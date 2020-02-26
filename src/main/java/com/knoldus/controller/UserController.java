package com.knoldus.controller;

import com.knoldus.repository.model.User;
import com.knoldus.repository.model.UserResponse;
import com.knoldus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/users/{id}")
    public Mono<ResponseEntity<User>> getUser(@PathVariable String id) {
        return userService.getUser(id)
                .map(user -> ResponseEntity.status(200).body(user));
    }
    
    @PostMapping("/users")
    public Mono<ResponseEntity<User>> createUser(@RequestBody User user) {
        return userService.createUser(user).map(response -> ResponseEntity.status(201).body(response));
    }
    
    @DeleteMapping("/users/{id}")
    public Mono<ResponseEntity<String>> deleteUser(@PathVariable String id) {
        return userService.deleteUserById(id).map(response -> ResponseEntity.status(200).body(response));
    }
    
    @PutMapping("/users")
    public Mono<ResponseEntity<User>> updateUser(@RequestBody User user) {
        return userService.updateUser(user).map(response -> ResponseEntity
                .status(200).body(response));
    }
    
    @GetMapping("/users")
    public Mono<ResponseEntity<UserResponse>> getUsers() {
        return userService.getAllUsers().collectList().map(users -> ResponseEntity
                .status(200).body(UserResponse.builder().users(users).build()));
    }
}
