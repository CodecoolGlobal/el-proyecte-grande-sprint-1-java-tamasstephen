package com.example.demo.dao.implementation;

import com.example.demo.dao.ContentDao;
import com.example.demo.model.user.Category;
import com.example.demo.model.user.CreatorProfile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("componentMem")
public class ContentDaoMem implements ContentDao {

    private static final ArrayList<CreatorProfile> CREATOR_PROFILES = new ArrayList<>();

    @Override
    public List<CreatorProfile> get(String creatorName) {
        return CREATOR_PROFILES.stream().filter(content -> content.hasCreatorSameName(creatorName)).collect(Collectors.toList());
    }

    @Override
    public Optional<CreatorProfile> get(long userId) {
        return CREATOR_PROFILES.stream().filter(content -> content.hasSameId(userId)).findAny();
    }

    @Override
    public Optional<CreatorProfile> getCreatorPageByPageLink(String pageLink) {
        return CREATOR_PROFILES.stream().filter(content -> content.hasSamePageLink(pageLink)).findAny();
    }

    @Override
    public List<CreatorProfile> getContentsByCategory(Category category) {
        return CREATOR_PROFILES.stream().filter(content -> content.hasSameCategory(category)).collect(Collectors.toList());
    }

    @Override
    public void add(CreatorProfile creatorProfile) {
        CREATOR_PROFILES.add(creatorProfile);
    }

    @Override
    public List<CreatorProfile> getAll() {
        return CREATOR_PROFILES;
    }

    @Override
    public boolean isPageLinkUnique(String pageLink){
        Optional<CreatorProfile> cnt = CREATOR_PROFILES.stream().filter(content -> content.hasSamePageLink(pageLink)).findFirst();
        return cnt.isEmpty();
    }
}
