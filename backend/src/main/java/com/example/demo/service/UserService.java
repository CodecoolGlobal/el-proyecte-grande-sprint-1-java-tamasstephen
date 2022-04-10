package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(UserEntity userEntity){
        userDao.add(userEntity);
    }

    public List<UserEntity> getAllUsers(){
        return userDao.getAll();
    }

    public Optional<UserEntity> getUser(long id){
        return userDao.get(id);
    }

    public boolean isEmailAvailable(String email){
        return userDao.isEmailAvailable(email);
    }

    public Optional<UserEntity> getUserByEmail(String email){
        return userDao.getUserByEmail(email);
    }

    public void deleteUser(UserEntity userEntity){
        userDao.deleteUser(userEntity);
    }

    public void updateUser(UserEntity prevUserEntity, UserEntity newUserEntity){
        userDao.update(prevUserEntity, newUserEntity);
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
