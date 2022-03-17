package com.example.demo.dao.implementation;

import com.example.demo.dao.UserDao;
import com.example.demo.model.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userMem")
public class UserDaoMem implements UserDao {

    private static final ArrayList<User> users = new ArrayList<>();

    @Override
    public List<User> get(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).collect(Collectors.toList());
    }

    @Override
    public Optional<User> get(long id) {
        return users.stream().filter(user -> user.hasSameId(id)).findFirst();
    }


    @Override
    public void add(User user) {
        long id = users.size() + 1;
        user.setId(id);
        users.add(user);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return users.stream().filter(user -> user.isMatchingEmail(email)).findFirst().isEmpty();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return users.stream().filter(user -> user.isMatchingEmail(email)).findFirst();
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user);
    }


}
