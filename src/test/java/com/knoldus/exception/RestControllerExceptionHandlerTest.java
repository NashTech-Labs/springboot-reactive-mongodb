package com.knoldus.exception;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RestControllerExceptionHandlerTest {
    
    @InjectMocks
    RestControllerExceptionHandler restControllerExceptionHandler;
    
    @Test
    public void testHandleUserNotFoundException() {
        UserNotFoundException userNotFoundException = new UserNotFoundException("User with id 1 not found.");
        ResponseEntity<ApiException> apiExceptionResponseEntity = restControllerExceptionHandler
                .handleUserNotFoundException(userNotFoundException);
        
        assertThat(apiExceptionResponseEntity, is(Matchers.notNullValue()));
        assertThat(apiExceptionResponseEntity.getStatusCode(), is(equalTo(HttpStatus.NOT_FOUND)));
        assertThat(apiExceptionResponseEntity.getBody(), is(instanceOf(ApiException.class)));
        assertThat(apiExceptionResponseEntity.getBody().getError(), is(equalTo("User with id 1 not found.")));
    }
    
    @Test
    public void testHandleUserAlreadyExistsException() {
        UserAlreadyExistsException userAlreadyExistsException = new UserAlreadyExistsException("User with id 1 already exists.");
        ResponseEntity<ApiException> apiExceptionResponseEntity = restControllerExceptionHandler
                .handleUserAlreadyExistsException(userAlreadyExistsException);
        
        assertThat(apiExceptionResponseEntity, is(Matchers.notNullValue()));
        assertThat(apiExceptionResponseEntity.getStatusCode(), is(equalTo(HttpStatus.CONFLICT)));
        assertThat(apiExceptionResponseEntity.getBody(), is(instanceOf(ApiException.class)));
        assertThat(apiExceptionResponseEntity.getBody().getError(), is(equalTo("User with id 1 already exists.")));
        
    }
}
