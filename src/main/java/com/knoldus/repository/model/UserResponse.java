package com.knoldus.repository.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserResponse {
    
    List<User> users;
}
