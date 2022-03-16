package com.example.demo.dao;

import com.example.demo.model.user.Category;
import com.example.demo.model.user.CreatorProfile;

import java.util.List;
import java.util.Optional;

public interface ContentDao {

    List<CreatorProfile> get(String creatorName);

    Optional<CreatorProfile> get(long userId);

    Optional<CreatorProfile> getCreatorPageByPageLink(String pageLink);

    List<CreatorProfile> getContentsByCategory(Category category);

    void add(CreatorProfile creatorProfile);

    List<CreatorProfile> getAll();

    boolean isPageLinkUnique(String pageLink);
}
