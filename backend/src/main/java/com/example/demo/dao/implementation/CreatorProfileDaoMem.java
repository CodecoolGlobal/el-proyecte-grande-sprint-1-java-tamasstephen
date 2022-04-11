package com.example.demo.dao.implementation;

import com.example.demo.dao.CreatorProfileDao;
import com.example.demo.model.category.Category;
import com.example.demo.model.user.CreatorProfile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("componentMem")
public class CreatorProfileDaoMem implements CreatorProfileDao {

    private static final ArrayList<CreatorProfile> CREATOR_PROFILES = new ArrayList<>();

    @Override
    public List<CreatorProfile> get(String creatorName) {
        return CREATOR_PROFILES.stream().filter(content -> content.hasCauseStringFragment(creatorName)).collect(Collectors.toList());
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

    @Override
    public void deleteCreatorProfile(CreatorProfile profile){
        CREATOR_PROFILES.remove(profile);
    }

    @Override
    public void update(CreatorProfile oldProfile, CreatorProfile newProfile) {
        newProfile.setUserEntity(oldProfile.getUserEntity());
        CREATOR_PROFILES.remove(oldProfile);
        CREATOR_PROFILES.add(newProfile);
    }
}
