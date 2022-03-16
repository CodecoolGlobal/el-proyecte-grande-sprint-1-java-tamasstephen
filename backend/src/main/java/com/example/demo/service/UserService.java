package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.user.User;
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

    public void add(User user){
        userDao.add(user);
    }

    public List<User> getAllUsers(){
        return userDao.getAll();
    }

    public Optional<User> getUser(long id){
        return userDao.get(id);
    }

}
