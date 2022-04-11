package com.example.demo.service;

import com.example.demo.dao.implementation.UserJpaDao;
import com.example.demo.model.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final UserJpaDao userDao;

    @Autowired
    public UserService(UserJpaDao userDao) {
        this.userDao = userDao;
    }

    public void add(UserEntity userEntity){
        userDao.save(userEntity);
    }

    public List<UserEntity> getAllUsers(){
        return userDao.findAll();
    }

    public Optional<UserEntity> getUser(long id){
        return userDao.findById(id);
    }

    public boolean isEmailAvailable(String email){
        return userDao.findByEmail(email).isEmpty();
    }

    public Optional<UserEntity> getUserByEmail(String email){
        return userDao.findByEmail(email);
    }

    public void deleteUser(UserEntity userEntity){
        userDao.delete(userEntity);
    }

    public void updateUser(UserEntity prevUserEntity, UserEntity newUserEntity){
        long id = prevUserEntity.getId();
        newUserEntity.setId(id);
        userDao.delete(prevUserEntity);
        userDao.save(newUserEntity);
    }

    public void updateEmail(UserEntity prevUserEntity, String email){
        UserEntity newUserEntity = UserEntity.builder()
                .email(email)
                .userCreatorProfileId(prevUserEntity.getUserCreatorProfileId())
                .password(prevUserEntity.getPassword())
                .id(prevUserEntity.getId())
                .build();
        updateUser(prevUserEntity, newUserEntity);

    }

}
