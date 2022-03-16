package com.example.demo.dao;

import com.example.demo.model.user.Category;
import com.example.demo.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> get(String name);

    Optional<User> get(long id);

    Optional<User> getUserByPageLink(String pageLink);

    List<User> getUsersByCategory(Category category);

    void add(User user);

    List<User> getAll();
}
