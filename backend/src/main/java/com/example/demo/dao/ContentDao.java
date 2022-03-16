package com.example.demo.dao;

import com.example.demo.model.user.Category;
import com.example.demo.model.user.Content;
import com.example.demo.model.user.User;

import java.util.List;
import java.util.Optional;

public interface ContentDao {

    List<Content> get(String creatorName);

    Optional<Content> get(long userId);

    Optional<Content> getCreatorPageByPageLink(String pageLink);

    List<Content> getContentsByCategory(Category category);

    void add(Content content);

    List<Content> getAll();

    boolean isPageLinkUnique(String pageLink);
}
