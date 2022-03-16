package com.example.demo.dao.implementation;

import com.example.demo.dao.UserDao;
import com.example.demo.model.user.Category;
import com.example.demo.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDaoMem implements UserDao {

    private List<User> users;

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

    @Override
    public void deleteUser(long id) {
        User userStream = users.stream().filter(user -> user.hasSameId(id)).collect(Collectors.toList()).get(0);
        users.remove(userStream);
    }
}
