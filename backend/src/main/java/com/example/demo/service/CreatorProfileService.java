package com.example.demo.service;

import com.example.demo.dao.CreatorProfileDao;
import com.example.demo.dao.implementation.CauseProfileJpaDao;
import com.example.demo.model.category.Category;
import com.example.demo.model.user.CreatorProfile;
import com.example.demo.model.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CreatorProfileService {

    private final CreatorProfileDao creatorProfileDao;

    @Autowired
    public CreatorProfileService(CreatorProfileDao creatorProfileDao) {
        this.creatorProfileDao = creatorProfileDao;
    }

    public List<CreatorProfile> get(String creatorName){
        return creatorProfileDao.getAll()
                .stream()
                .filter(cause -> cause.getCauseName()
                        .contains(creatorName))
                .collect(Collectors.toList());
    }

    public Optional<CreatorProfile> get(long userId){
        return creatorProfileDao.get(userId);
    }

    public Optional<CreatorProfile> get(UserEntity user){
        return Optional.of(user.getCauseProfile());
    }

    public Optional<CreatorProfile> getCreatorPageByPageLink(String pageLink){
        return creatorProfileDao.getCreatorPageByPageLink(pageLink);
    }

    public List<CreatorProfile> getContentsByCategory(Category category){
        return creatorProfileDao.getContentsByCategory(category);
    }

    public CreatorProfile add(CreatorProfile creatorProfile){
        creatorProfileDao.add(creatorProfile);
        return creatorProfile;
    }

    public List<CreatorProfile> getAll(){
        return creatorProfileDao.getAll();
    }

    public boolean isPageLinkUnique(String pageLink){
        // TODO: return findByPageLink().isEmpty();
        return creatorProfileDao.getCreatorPageByPageLink(pageLink).isEmpty();
    }

    // TODO: create profile update

    public void updateCreatorProfile(CreatorProfile oldProfile, CreatorProfile newProfile){
        oldProfile.setCauseName(newProfile.getCauseName());
        oldProfile.setDescription(newProfile.getDescription());
        creatorProfileDao.update(oldProfile, newProfile);
    }

    public void updateProfileDescription(CreatorProfile profile, String description){
            CreatorProfile newProfile = CreatorProfile.builder()
                    .description(description)
                    .causeName(profile.getCauseName())
                    .profileImage(profile.getProfileImage())
                    .id(profile.getId())
                    .category(profile.getCategory())
                    .build();
            creatorProfileDao.deleteCreatorProfile(profile);
            creatorProfileDao.add(newProfile);

    }

    public void updateProfileTitle(CreatorProfile profile, String title){
        CreatorProfile newProfile = CreatorProfile.builder()
                .description(profile.getDescription())
                .causeName(title)
                .profileImage(profile.getProfileImage())
                .id(profile.getId())
                .category(profile.getCategory())
                .build();
        creatorProfileDao.deleteCreatorProfile(profile);
        creatorProfileDao.add(newProfile);

    }

            }
