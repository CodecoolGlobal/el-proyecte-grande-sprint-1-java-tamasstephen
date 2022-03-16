package com.example.demo.service;

import com.example.demo.dao.ContentDao;
import com.example.demo.model.user.Category;
import com.example.demo.model.user.Content;
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

    public List<Content> get(String creatorName){
        return contentDao.get(creatorName);
    }

    public Optional<Content> get(long userId){
        return contentDao.get(userId);
    }

    public Optional<Content> getCreatorPageByPageLink(String pageLink){
        return contentDao.getCreatorPageByPageLink(pageLink);
    }

    public List<Content> getContentsByCategory(Category category){
        return contentDao.getContentsByCategory(category);
    }

    public void add(Content content){
        contentDao.add(content);
    }

    public List<Content> getAll(){
        return contentDao.getAll();
    }

    public boolean isPageLinkUnique(String pageLink){
        return contentDao.isPageLinkUnique(pageLink);
    }
}
