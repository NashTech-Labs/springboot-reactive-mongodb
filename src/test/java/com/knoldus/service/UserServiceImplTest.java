package com.knoldus.service;

import com.knoldus.exception.UserNotFoundException;
import com.knoldus.repository.UserRepository;
import com.knoldus.repository.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Constructor;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    
    @Test
    public void shouldReturnUserById() {
        User user = User.builder().id("1").name("Deepak").address("Delhi").age("28").build();
        Mockito.when(userRepository.findById("1")).thenReturn(Mono.just(user));
        Mono<User> userMono = userServiceImpl.getUser("1");
        User result = userMono.block();
        
        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is("1"));
        assertThat(result.getName(), is("Deepak"));
        assertThat(result.getAddress(), is("Delhi"));
        assertThat(result.getAge(), is("28"));
    }
    
    @Test
    public void shouldThrowUserNotFound() {
        Mockito.when(userRepository.findById("1")).thenReturn(Mono.empty());
        try {
            userServiceImpl.getUser("1").block();
        } catch (UserNotFoundException ue) {
            assertThat(ue, is(instanceOf(UserNotFoundException.class)));
            assertThat(ue.getMessage(), is(equalTo("User with id 1 not found. Please check and try again.")));
        }
    }
    
    @Test
    public void shouldCreateUser() {
        User user = User.builder().id("1").name("Deepak").address("Delhi").age("28").build();
        Mockito.when(userRepository.existsById("1")).thenReturn(Mono.just(Boolean.FALSE));
        Mockito.when(userRepository.save(user)).thenReturn(Mono.just(user));
        Mono<User> userMono = userServiceImpl.createUser(user);
        User result = userMono.block();
        
        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is("1"));
        assertThat(result.getName(), is("Deepak"));
        assertThat(result.getAddress(), is("Delhi"));
        assertThat(result.getAge(), is("28"));
    }
    
    @Test
    public void shouldUpdateUser() {
        User user = User.builder().id("1").name("Deepak").address("Delhi").age("28").build();
        Mockito.when(userRepository.save(user)).thenReturn(Mono.just(user));
        Mono<User> userMono = userServiceImpl.updateUser(user);
        User result = userMono.block();
        
        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is("1"));
        assertThat(result.getName(), is("Deepak"));
        assertThat(result.getAddress(), is("Delhi"));
        assertThat(result.getAge(), is("28"));
    }
    
    @Test
    public void shouldReturnAllUsers() {
        User user = User.builder().id("1").name("Deepak").address("Delhi").age("28").build();
        User user1 = User.builder().id("2").name("Kanika").address("Delhi").age("27").build();
        Mockito.when(userRepository.findAll()).thenReturn(Flux.just(user, user1));
        Flux<User> userFlux = userServiceImpl.getAllUsers();
        List<User> result = userFlux.collectList().block();
        
        assertThat(result, is(notNullValue()));
        assertThat(result, hasSize(2));
    }
    
    @Test
    public void shouldDeleteUserById() throws Exception {
        Constructor<Void> declaredConstructors = Void.class.getDeclaredConstructor();
        declaredConstructors.setAccessible(true);
        Void v = declaredConstructors.newInstance();
        Mockito.when(userRepository.existsById("1"))
                .thenReturn(Mono.just(Boolean.TRUE));
        Mockito.when(userRepository.deleteById("1"))
                .thenReturn(Mono.just(v));
        Mono<String> userMono = userServiceImpl.deleteUserById("1");
        String result = userMono.block();
        
        assertThat(result, is(notNullValue()));
        assertThat(result, is(equalTo("User 1 successfully deleted.")));
    }
}
