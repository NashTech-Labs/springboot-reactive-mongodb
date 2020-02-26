package com.knoldus.controller;

import com.knoldus.repository.model.User;
import com.knoldus.repository.model.UserResponse;
import com.knoldus.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    
    @Mock
    private UserService userService;
    
    @InjectMocks
    private UserController userController;
    
    @Test
    public void shouldReturnUser() {
        User user = User.builder().id("1").name("Deepak").address("Delhi").age("28").build();
        Mockito.when(userService.getUser("1")).thenReturn(Mono.just(user));
        Mono<ResponseEntity<User>> userMono = userController.getUser("1");
        ResponseEntity<User> result = userMono.block();
        
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.OK)));
        User userBody = result.getBody();
        assertThat(userBody.getId(), is(equalTo("1")));
        assertThat(userBody.getName(), is("Deepak"));
        assertThat(userBody.getAddress(), is("Delhi"));
        assertThat(userBody.getAge(), is("28"));
    }
    
    @Test
    public void shouldCreateUser() {
        User user = User.builder().id("1").name("Deepak").address("Delhi").age("28").build();
        Mockito.when(userService.createUser(user)).thenReturn(Mono.just(user));
        Mono<ResponseEntity<User>> userMono = userController.createUser(user);
        ResponseEntity<User> result = userMono.block();
        
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.CREATED)));
        User userBody = result.getBody();
        assertThat(userBody.getId(), is(equalTo("1")));
        assertThat(userBody.getName(), is("Deepak"));
        assertThat(userBody.getAddress(), is("Delhi"));
        assertThat(userBody.getAge(), is("28"));
    }
    
    @Test
    public void shouldUpdateUser() {
        User user = User.builder().id("1").name("Deepak").address("Delhi").age("28").build();
        Mockito.when(userService.updateUser(user)).thenReturn(Mono.just(user));
        Mono<ResponseEntity<User>> userMono = userController.updateUser(user);
        ResponseEntity<User> result = userMono.block();
        
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.OK)));
        User userBody = result.getBody();
        assertThat(userBody.getId(), is(equalTo("1")));
        assertThat(userBody.getName(), is("Deepak"));
        assertThat(userBody.getAddress(), is("Delhi"));
        assertThat(userBody.getAge(), is("28"));
    }
    
    @Test
    public void shouldDeleteUser() {
        User user = User.builder().id("1").name("Deepak").address("Delhi").age("28").build();
        Mockito.when(userService.deleteUserById("1")).thenReturn(Mono.just("User 1 successfully deleted."));
        Mono<ResponseEntity<String>> userMono = userController.deleteUser("1");
        ResponseEntity<String> result = userMono.block();
        
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.OK)));
        String actualResult = result.getBody();
        assertThat(actualResult, is(equalTo("User 1 successfully deleted.")));
    }
    
    @Test
    public void shouldReturnAllUsers() {
        User user = User.builder().id("1").name("Deepak").address("Delhi").age("28").build();
        User user1 = User.builder().id("2").name("Kanika").address("Delhi").age("27").build();
        Mockito.when(userService.getAllUsers()).thenReturn(Flux.just(user, user1));
        Mono<ResponseEntity<UserResponse>> userMono = userController.getUsers();
        ResponseEntity<UserResponse> result = userMono.block();
        
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.OK)));
        List<User> userBody = result.getBody().getUsers();
        assertThat(userBody, hasSize(equalTo(2)));
    }
}
