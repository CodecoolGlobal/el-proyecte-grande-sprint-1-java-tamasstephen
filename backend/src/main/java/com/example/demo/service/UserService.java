package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.user.Category;
import com.example.demo.model.user.User;

import java.util.List;
import java.util.Optional;

public class UserService {

    UserDao userDao;

    public void add(User user){
        userDao.add(user);
    }

    public List<User> getUsersByCategory(Category category){
        return userDao.getUsersByCategory(category);
    }

    public List<User> getAllUsers(){
        return userDao.getAll();
    }

    public Optional<User> getUser(long id){
        return userDao.get(id);
    }

    public List<User> getUser(String name){
        return userDao.get(name);
    }

    public Optional<User> getUserByPageLink(String pageLink){
        return userDao.getUserByPageLink(pageLink);
    }

    public void deleteUser(long id){
        userDao.deleteUser(id);
    }

}
