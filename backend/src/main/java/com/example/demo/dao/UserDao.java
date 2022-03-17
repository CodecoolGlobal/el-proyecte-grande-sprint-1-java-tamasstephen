package com.example.demo.dao;

import com.example.demo.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> get(String email);

    Optional<User> get(long id);

    void add(User user);

    List<User> getAll();

    boolean isEmailAvailable(String email);

    Optional<User> getUserByEmail(String email);

}
