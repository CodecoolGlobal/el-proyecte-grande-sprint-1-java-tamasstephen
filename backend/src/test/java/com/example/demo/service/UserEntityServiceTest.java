package com.example.demo.service;

import com.example.demo.dao.implementation.UserJpaDao;
import com.example.demo.model.user.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserEntityServiceTest {

    @Mock
    UserJpaDao userJpaDao;

    private UserService userService;
    private UserEntity userEntity;
    private UserEntity secondUserEntity;

    @BeforeEach
    void init(){
        userEntity = UserEntity.builder().email("hey@hey.com").password("Happy Human").build();
        secondUserEntity = UserEntity.builder().email("ho@hoho.com").password("Very Happy Human").build();
        userService = new UserService(userJpaDao);
    }

    @Test
    void add_addsUserToUsers_userIsInTheMemory(){
        when(userJpaDao.save(userEntity)).thenReturn(userEntity);
        userService.add(userEntity);

        verify(userJpaDao, atLeastOnce()).save(userEntity);
    }

    @Test
    void getAllUsers_returnsAllUsers_returnsListWithUsers(){
        when(userJpaDao.findAll()).thenReturn(List.of(userEntity, secondUserEntity));

        List<UserEntity> result = userService.getAllUsers();

        assertTrue(result.size() > 0);
    }

    @Test
    void getUser_returnsUserById_returnsValidUser(){

        long id = 2;

        when(userJpaDao.findById(id)).thenReturn(Optional.of(secondUserEntity));

        Optional<UserEntity> result = userService.getUser(id);

        assertTrue(result.isPresent());
    }

    @Test
    void getUser_returnsUserById_returnsNothing(){

        long invalidId = 100000001;
        when(userJpaDao.findById(invalidId)).thenReturn(Optional.empty());
        Optional<UserEntity> result = userService.getUser(invalidId);

        assertTrue(result.isEmpty());
    }

    @Test
    void isEmailAvailable_returnsTrueIfEmailDosNotExists_returnTrue(){

        String email = "validEmail";

        when(userJpaDao.findByEmail(email)).thenReturn(Optional.empty());

        boolean result = userService.isEmailAvailable(email);

        assertTrue(result);
    }


    @Test
    void isEmailAvailable_returnsTrueIfEmailDosNotExists_returnFalse(){
        String email = "validEmail";

        when(userJpaDao.findByEmail(email)).thenReturn(Optional.of(userEntity));

        boolean result = userService.isEmailAvailable(email);

        assertFalse(result);
    }

    @Test
    void getUserByEmail_returnsUserByEmail_returnsUser(){
        String email = "validEmail";

        when(userJpaDao.findByEmail(email)).thenReturn(Optional.of(userEntity));

        Optional<UserEntity> result = userService.getUserByEmail(email);

        assertTrue(result.isPresent());
    }

    @Test
    void getUserByEmail_returnsUserByEmail_returnsNothing(){
        String email = "validEmail";

        when(userJpaDao.findByEmail(email)).thenReturn(Optional.empty());

        Optional<UserEntity> result = userService.getUserByEmail(email);

        assertTrue(result.isEmpty());
    }

    @Test
    void update_updatesUserDetailsWithNewInformation_changesDetails(){
        when(userJpaDao.save(userEntity)).thenReturn(userEntity);

        userService.updateUser(secondUserEntity, userEntity);

        verify(userJpaDao, atLeastOnce()).save(secondUserEntity);
    }

    @Test
    void deleteUser_removesUserFromMem_removesUser(){

        userService.deleteUser(userEntity);

        verify(userJpaDao, atLeastOnce()).delete(userEntity);
    }

    @Test
    void updateEmail_updatesUserEmail_updatesMail(){

        userService.updateUser(userEntity, secondUserEntity);

        verify(userJpaDao, atLeastOnce()).save(userEntity);
    }

}