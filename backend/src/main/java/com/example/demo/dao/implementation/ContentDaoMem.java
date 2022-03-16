package com.example.demo.dao.implementation;

import com.example.demo.dao.ContentDao;
import com.example.demo.model.user.Category;
import com.example.demo.model.user.Content;
import com.example.demo.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContentDaoMem implements ContentDao {

    private static final ArrayList<User> users = new ArrayList<>();

    @Override
    public List<Content> get(String creatorName) {
        return null;
    }

    @Override
    public Optional<Content> get(long userId) {
        return Optional.empty();
    }

    @Override
    public Optional<Content> getCreatorPageByPageLink(String pageLink) {
        return Optional.empty();
    }

    @Override
    public List<Content> getContentsByCategory(Category category) {
        return null;
    }

    @Override
    public void add(Content content) {

    }

    @Override
    public List<Content> getAll() {
        return null;
    }
}
