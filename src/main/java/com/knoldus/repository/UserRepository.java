package com.knoldus.repository;

import com.knoldus.repository.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    
    Mono<User> save(User user);
    
    Mono<User> findById(String id);
    
    Flux<User> findAll();
    
    Mono<Void> deleteById(String id);
    
    Mono<Boolean> existsById(String id);
}
