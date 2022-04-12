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

    private final CauseProfileJpaDao creatorProfileDao;

    @Autowired
    public CreatorProfileService(CauseProfileJpaDao creatorProfileDao) {
        this.creatorProfileDao = creatorProfileDao;
    }

    public List<CreatorProfile> get(String creatorName){
        return creatorProfileDao.findAll()
                .stream()
                .filter(cause -> cause.getCauseName()
                        .contains(creatorName))
                .collect(Collectors.toList());
    }

    public Optional<CreatorProfile> get(long userId){
        return creatorProfileDao.findById(userId);
    }

    public Optional<CreatorProfile> get(UserEntity user){
        return creatorProfileDao.findByUserEntity(user);
    }

    public Optional<CreatorProfile> getCreatorPageByPageLink(String pageLink){
        return creatorProfileDao.findByPageLink(pageLink);
    }

    public List<CreatorProfile> getContentsByCategory(Category category){
        return creatorProfileDao.findByCategory(category);
    }

    public CreatorProfile add(CreatorProfile creatorProfile){
        return creatorProfileDao.saveAndFlush(creatorProfile);
    }

    public List<CreatorProfile> getAll(){
        return creatorProfileDao.findAll();
    }

    public boolean isPageLinkUnique(String pageLink){
        // TODO: return findByPageLink().isEmpty();
        return creatorProfileDao.findByPageLink(pageLink).isEmpty();
    }

    // TODO: create profile update

    public void updateCreatorProfile(CreatorProfile oldProfile, CreatorProfile newProfile){
        oldProfile.setCauseName(newProfile.getCauseName());
        oldProfile.setDescription(newProfile.getDescription());
        creatorProfileDao.saveAndFlush(oldProfile);
    }

    public void updateProfileDescription(CreatorProfile profile, String description){
        profile.setDescription(description);
        creatorProfileDao.saveAndFlush(profile);
    }

    public void updateProfileTitle(CreatorProfile profile, String title){
        profile.setCauseName(title);
        creatorProfileDao.saveAndFlush(profile);

    }

}
