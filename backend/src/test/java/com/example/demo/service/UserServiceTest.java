package com.example.demo.service;

import com.example.demo.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    private User user;
    private User secondUser;

    @BeforeEach
    void init(){
        user = User.builder().email("hey@hey.com").password("Happy Human").build();
        secondUser =User.builder().email("ho@hoho.com").password("Very Happy Human").build();
    }

    @Test
    void add_addsUserToUsers_userIsInTheMemory(){
        userService.add(user);

        int result = userService.getAllUsers().size();

        assertTrue(result > 0);
    }

    @Test
    void getAllUsers_returnsAllUsers_returnsListWithUsers(){
        userService.add(user);

        List<User> result = userService.getAllUsers();

        assertTrue(result.size() > 0);
    }

    @Test
    void getUser_returnsUserById_returnsValidUser(){
        userService.add(secondUser);
        long id = secondUser.getId();

        Optional<User> result = userService.getUser(id);

        assertTrue(result.isPresent());
    }

    @Test
    void getUser_returnsUserById_returnsNothing(){

        long invalidId = 100000001;
        Optional<User> result = userService.getUser(invalidId);

        assertTrue(result.isEmpty());
    }

    @Test
    void isEmailAvailable_returnsTrueIfEmailDosNotExists_returnTrue(){
        boolean result = userService.isEmailAvailable("aValidOption@option.com");

        assertTrue(result);
    }


    @Test
    void isEmailAvailable_returnsTrueIfEmailDosNotExists_returnFalse(){
        userService.add(user);
        boolean result = userService.isEmailAvailable("hey@hey.com");

        assertFalse(result);
    }

    @Test
    void getUserByEmail_returnsUserByEmail_returnsUser(){
        userService.add(user);

        Optional<User> result = userService.getUserByEmail("hey@hey.com");

        assertTrue(result.isPresent());
    }

    @Test
    void getUserByEmail_returnsUserByEmail_returnsNothing(){
        userService.add(user);

        Optional<User> result = userService.getUserByEmail("nonexisting@hey.com");

        assertTrue(result.isEmpty());
    }

    @Test
    void update_updatesUserDetailsWithNewInformation_changesDetails(){
        User uniqueUser = User.builder().email("unique@un.com").password("unique").build();
        userService.add(uniqueUser);

        long id = uniqueUser.getId();
        userService.updateUser(uniqueUser, user);

        assertEquals(userService.getUser(id).get().getEmail(), user.getEmail());
    }

    @Test
    void delteUser_removesUserFromMem_removesUser(){
        User userToDelete =User.builder().email("delete@test.com").password("willBeDeleted").build();
        userService.add(userToDelete);

        int addedToList = userService.getAllUsers().size();

        userService.deleteUser(userToDelete);

        int result = userService.getAllUsers().size();

        assertTrue(addedToList > result);
    }

}