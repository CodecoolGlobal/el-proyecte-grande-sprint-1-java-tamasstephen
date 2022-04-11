package com.example.demo.dao;

import com.example.demo.model.user.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<UserEntity> get(String email);

    Optional<UserEntity> get(long id);

    void add(UserEntity userEntity);

    List<UserEntity> getAll();

    boolean isEmailAvailable(String email);

    Optional<UserEntity> getUserByEmail(String email);

    void deleteUser(UserEntity userEntity);

    void update(UserEntity prevUserEntity, UserEntity nextUserEntity);

}
