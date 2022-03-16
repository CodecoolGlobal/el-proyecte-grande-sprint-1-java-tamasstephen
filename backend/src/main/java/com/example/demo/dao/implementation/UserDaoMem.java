package com.example.demo.dao.implementation;

import com.example.demo.dao.UserDao;
import com.example.demo.model.user.Category;
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
    public List<User> get(String name) {
        return users.stream().filter(user -> user.getUserName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public Optional<User> get(long id) {
        return users.stream().filter(user -> user.hasSameId(id)).findFirst();
    }

    @Override
    public Optional<User> getUserByPageLink(String pageLink) {
        return users.stream().filter(user -> user.hasSameEndpoint(pageLink)).findFirst();
    }

    @Override
    public List<User> getUsersByCategory(Category category) {
        return users.stream().filter(user -> user.isSameCategory(category)).collect(Collectors.toList());
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public List<User> getAll() {
        return users;
    }
}