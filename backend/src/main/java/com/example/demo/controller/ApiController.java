package com.example.demo.controller;

import com.example.demo.model.user.Category;
import com.example.demo.model.user.CreatorProfile;
import com.example.demo.model.user.User;
import com.example.demo.service.ContentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContentService contentService;

    @PostMapping("/add-creator")
    public void add(@RequestBody User user, HttpSession session){
        long newId = userService.getAllUsers() == null ? 1 : userService.getAllUsers().size() + 1;
        user.setId(newId);
        userService.add(user);
        session.setAttribute("userId", newId);

    }

    @PostMapping("/add-content")
    public boolean add(@RequestBody CreatorProfile creatorProfile, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        if (isCreatorAvailableForCreation(userId, creatorProfile.getPageLink())){
            User user = userService.getUser(userId).get();
            creatorProfile.setUserId(userId);
            user.setContent(creatorProfile);
            contentService.add(creatorProfile);
            return true;
        }
        return false;
    }

    @GetMapping("/all-creators")
    public List<CreatorProfile> getAll(){
        return contentService.getAll();
    }

    @GetMapping("/creators")
    public List<CreatorProfile> get(@RequestParam String name){
        List<CreatorProfile> creatorProfile = contentService.get(name);
        return creatorProfile;
    }

    @GetMapping("/creator")
    public CreatorProfile getContentByLink(@RequestParam String pageLink){
        Optional<CreatorProfile> content = contentService.getCreatorPageByPageLink(pageLink);
        return content.isEmpty() ? null : content.get();
    }

    @GetMapping("/creators/category")
    public List<CreatorProfile> getCreatorsByCategory(@RequestParam Category category){
        System.out.println("hello");
        System.out.println(category);
        return contentService.getContentsByCategory(category);
    }

    @GetMapping("/user/")
    public boolean isUserContentSet(@RequestParam long id){
        Optional<User> userOption = userService.getUser(id);
        return userOption.map(User::isCreatorProfileAvailable).orElse(false);
    }

    @PutMapping("/creator")
    public void changeContent(@RequestBody CreatorProfile creatorProfile){
        Optional<CreatorProfile> contentOptional = contentService.getCreatorPageByPageLink(creatorProfile.getPageLink());
        //TODO: finish this
    }

    private boolean isCreatorAvailableForCreation(Long userId, String pageLink){
        System.out.println(pageLink);
       return userId != null
               && userService.getUser(userId).isPresent()
               && contentService.isPageLinkUnique(pageLink);
    }
}
