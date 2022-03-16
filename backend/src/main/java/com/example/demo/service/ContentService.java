package com.example.demo.service;

import com.example.demo.dao.ContentDao;
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
    private ContentDao contentDao;

    public List<CreatorProfile> get(String creatorName){
        return contentDao.get(creatorName);
    }

    public Optional<CreatorProfile> get(long userId){
        return contentDao.get(userId);
    }

    public Optional<CreatorProfile> getCreatorPageByPageLink(String pageLink){
        return contentDao.getCreatorPageByPageLink(pageLink);
    }

    public List<CreatorProfile> getContentsByCategory(Category category){
        return contentDao.getContentsByCategory(category);
    }

    public void add(CreatorProfile creatorProfile){
        contentDao.add(creatorProfile);
    }

    public List<CreatorProfile> getAll(){
        return contentDao.getAll();
    }

    public boolean isPageLinkUnique(String pageLink){
        return contentDao.isPageLinkUnique(pageLink);
    }
}
