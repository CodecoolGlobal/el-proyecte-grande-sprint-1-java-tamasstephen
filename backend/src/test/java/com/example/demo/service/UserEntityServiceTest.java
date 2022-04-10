package com.example.demo.service;

import com.example.demo.model.user.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserEntityServiceTest {

    @Autowired
    private UserService userService;

    private UserEntity userEntity;
    private UserEntity secondUserEntity;

    @BeforeEach
    void init(){
        userEntity = UserEntity.builder().email("hey@hey.com").password("Happy Human").build();
        secondUserEntity = UserEntity.builder().email("ho@hoho.com").password("Very Happy Human").build();
    }

    @Test
    void add_addsUserToUsers_userIsInTheMemory(){
        userService.add(userEntity);

        int result = userService.getAllUsers().size();

        assertTrue(result > 0);
    }

    @Test
    void getAllUsers_returnsAllUsers_returnsListWithUsers(){
        userService.add(userEntity);

        List<UserEntity> result = userService.getAllUsers();

        assertTrue(result.size() > 0);
    }

    @Test
    void getUser_returnsUserById_returnsValidUser(){
        userService.add(secondUserEntity);
        long id = secondUserEntity.getId();

        Optional<UserEntity> result = userService.getUser(id);

        assertTrue(result.isPresent());
    }

    @Test
    void getUser_returnsUserById_returnsNothing(){

        long invalidId = 100000001;
        Optional<UserEntity> result = userService.getUser(invalidId);

        assertTrue(result.isEmpty());
    }

    @Test
    void isEmailAvailable_returnsTrueIfEmailDosNotExists_returnTrue(){
        boolean result = userService.isEmailAvailable("aValidOption@option.com");

        assertTrue(result);
    }


    @Test
    void isEmailAvailable_returnsTrueIfEmailDosNotExists_returnFalse(){
        userService.add(userEntity);
        boolean result = userService.isEmailAvailable("hey@hey.com");

        assertFalse(result);
    }

    @Test
    void getUserByEmail_returnsUserByEmail_returnsUser(){
        userService.add(userEntity);

        Optional<UserEntity> result = userService.getUserByEmail("hey@hey.com");

        assertTrue(result.isPresent());
    }

    @Test
    void getUserByEmail_returnsUserByEmail_returnsNothing(){
        userService.add(userEntity);

        Optional<UserEntity> result = userService.getUserByEmail("nonexisting@hey.com");

        assertTrue(result.isEmpty());
    }

    @Test
    void update_updatesUserDetailsWithNewInformation_changesDetails(){
        UserEntity uniqueUserEntity = UserEntity.builder().email("unique@un.com").password("unique").build();
        userService.add(uniqueUserEntity);

        long id = uniqueUserEntity.getId();
        userService.updateUser(uniqueUserEntity, userEntity);

        assertEquals(userService.getUser(id).get().getEmail(), userEntity.getEmail());
    }

    @Test
    void delteUser_removesUserFromMem_removesUser(){
        UserEntity userEntityToDelete = UserEntity.builder().email("delete@test.com").password("willBeDeleted").build();
        userService.add(userEntityToDelete);

        int addedToList = userService.getAllUsers().size();

        userService.deleteUser(userEntityToDelete);

        int result = userService.getAllUsers().size();

        assertTrue(addedToList > result);
    }

}