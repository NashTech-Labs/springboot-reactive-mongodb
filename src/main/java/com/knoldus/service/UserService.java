package com.knoldus.service;

import com.knoldus.repository.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    
    Mono<User> getUser(String id);
    
    Flux<User> getAllUsers();
    
    Mono<String> deleteUserById(String id);
    
    Mono<User> createUser(User user);
    
    Mono<User> updateUser(User user);
}
