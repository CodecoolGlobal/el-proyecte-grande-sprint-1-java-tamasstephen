package com.example.demo.service;

import com.example.demo.dao.CreatorProfileDao;
import com.example.demo.model.user.Category;
import com.example.demo.model.user.CreatorProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ContentService {

    @Autowired
    @Qualifier("componentMem")
    private CreatorProfileDao creatorProfileDao;

    public List<CreatorProfile> get(String creatorName){
        return creatorProfileDao.get(creatorName);
    }

    public Optional<CreatorProfile> get(long userId){
        return creatorProfileDao.get(userId);
    }

    public Optional<CreatorProfile> getCreatorPageByPageLink(String pageLink){
        return creatorProfileDao.getCreatorPageByPageLink(pageLink);
    }

    public List<CreatorProfile> getContentsByCategory(Category category){
        return creatorProfileDao.getContentsByCategory(category);
    }

    public void add(CreatorProfile creatorProfile){
        creatorProfileDao.add(creatorProfile);
    }

    public List<CreatorProfile> getAll(){
        return creatorProfileDao.getAll();
    }

    public boolean isPageLinkUnique(String pageLink){
        return creatorProfileDao.isPageLinkUnique(pageLink);
    }
}
