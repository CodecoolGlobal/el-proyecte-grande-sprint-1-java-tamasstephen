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

    public boolean isEmailAvailable(String email){
        return userDao.isEmailAvailable(email);
    }

    public Optional<User> getUserByEmail(String email){
        return userDao.getUserByEmail(email);
    }

    public void deleteUser(User user){
        userDao.deleteUser(user);
    }

    public void updateUser(User prevUser, User newUser){
        userDao.update(prevUser, newUser);
    }

}
