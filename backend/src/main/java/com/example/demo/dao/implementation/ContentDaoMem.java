package com.example.demo.dao.implementation;

import com.example.demo.dao.ContentDao;
import com.example.demo.model.user.Category;
import com.example.demo.model.user.Content;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("componentMem")
public class ContentDaoMem implements ContentDao {

    private static final ArrayList<Content> contents = new ArrayList<>();

    @Override
    public List<Content> get(String creatorName) {
        return contents.stream().filter(content -> content.hasCreatorSameName(creatorName)).collect(Collectors.toList());
    }

    @Override
    public Optional<Content> get(long userId) {
        return contents.stream().filter(content -> content.hasSameId(userId)).findAny();
    }

    @Override
    public Optional<Content> getCreatorPageByPageLink(String pageLink) {
        return contents.stream().filter(content -> content.hasSamePageLink(pageLink)).findAny();
    }

    @Override
    public List<Content> getContentsByCategory(Category category) {
        return contents.stream().filter(content -> content.hasSameCategory(category)).collect(Collectors.toList());
    }

    @Override
    public void add(Content content) {
        contents.add(content);
    }

    @Override
    public List<Content> getAll() {
        return contents;
    }

    @Override
    public boolean isPageLinkUnique(String pageLink){
        Optional<Content> cnt = contents.stream().filter(content -> content.hasSamePageLink(pageLink)).findFirst();
        return cnt.isEmpty();
    }
}
