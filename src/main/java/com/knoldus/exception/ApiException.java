package com.knoldus.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiException {
    
    private LocalDateTime timestamp;
    
    private Integer status;
    
    private String error;
    
}
