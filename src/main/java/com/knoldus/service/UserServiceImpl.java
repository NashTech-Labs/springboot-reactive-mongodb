package com.knoldus.service;

import com.knoldus.exception.UserAlreadyExistsException;
import com.knoldus.exception.UserNotFoundException;
import com.knoldus.repository.UserRepository;
import com.knoldus.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public Mono<User> getUser(String id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException("User with id " + id + " not found. Please "
                        + "check and try again.")));
    }
    
    @Override
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public Mono<String> deleteUserById(String id) {
        return userRepository.existsById(id).flatMap(ifExists -> {
            if (ifExists.equals(Boolean.FALSE)) {
                
                throw new UserNotFoundException("User with id " + id + " not found. Please "
                        + "check and try again.");
            }
            return userRepository.deleteById(id).map(ignore -> "User " + id + " successfully deleted.");
        });
    }
    
    @Override
    public Mono<User> createUser(User user) {
        return userRepository.existsById(user.getId()).flatMap(ifExists -> {
            if (ifExists.equals(Boolean.TRUE)) {
                throw new UserAlreadyExistsException("User with id " + user.getId() + " already exists.");
            }
            
            return userRepository.save(user);
        });
    }
    
    @Override
    public Mono<User> updateUser(User user) {
        return userRepository.save(user);
    }
}

