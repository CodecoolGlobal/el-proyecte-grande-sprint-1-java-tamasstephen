package com.example.demo.dao;

import com.example.demo.model.user.Category;
import com.example.demo.model.user.User;

import java.util.List;

public interface UserDao {

    User get(String name);

    User get(long id);

    User getUserByPageLink(String pageLink);

    List<User> getUsersByCategory(Category category);

    void add(User user);

    List<User> getAll();
}
