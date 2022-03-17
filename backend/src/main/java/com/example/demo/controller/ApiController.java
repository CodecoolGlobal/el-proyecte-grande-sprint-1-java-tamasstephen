package com.example.demo.controller;

import com.example.demo.model.user.Category;
import com.example.demo.model.user.CreatorProfile;
import com.example.demo.model.user.User;
import com.example.demo.service.CreatorProfileService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {

    // TODO: replace if else statements with try catch and return helpful return messages

    private final UserService userService;
    private final CreatorProfileService creatorProfileService;

    @Autowired
    public ApiController(UserService userService, CreatorProfileService creatorProfileService) {
        this.userService = userService;
        this.creatorProfileService = creatorProfileService;
    }

    @PostMapping("/user")
    public boolean add(@RequestBody User user, HttpSession session){
        if (userService.isEmailAvailable(user.getEmail())){
            userService.add(user);
            session.setAttribute("userId", user.getId());
            return true;
        }
        return false;
    }

    @PostMapping("/login")
    public long login(@RequestBody User user, HttpSession session){
        Optional<User> userOptional = userService.getUserByEmail(user.getEmail());
        if (userOptional.isPresent() && userOptional.get().isValidPassword(user.getPassword())){
            User regUser = userOptional.get();
            session.setAttribute("userId", regUser.getId());
            return regUser.getId();
        }
        return 0;
    }

    @GetMapping("/user/profile")
    public User getUserProfile(HttpSession session){
        Long id = (Long) session.getAttribute("userId");
        Optional<User> userOptional;
        if (id != null && (userOptional = userService.getUser(id)).isPresent()){
            return userOptional.get();
        }
        return null;
    }

    @PostMapping("/creator-profile")
    public boolean addCreatorProfile(@RequestBody CreatorProfile creatorProfile, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        if (isCreatorAvailableForCreation(userId, creatorProfile.getPageLink())){
            User user = userService.getUser(userId).get();
            creatorProfile.setUserId(userId);
            user.setContent(creatorProfile);
            creatorProfileService.add(creatorProfile);
            return true;
        }
        return false;
    }

    @GetMapping("/creator-profile-set/")
    public boolean isUserContentSet(@RequestParam long id){
        Optional<User> userOption = userService.getUser(id);
        return userOption.map(User::isCreatorProfileAvailable).orElse(false);
    }

    @PutMapping("/creator-profile")
    public boolean changeContent(@RequestBody CreatorProfile creatorProfile, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        Optional<CreatorProfile> profile;
        if (userId != null && (profile = creatorProfileService.get(userId)).isPresent()){
            CreatorProfile prevProfile = profile.get();
            creatorProfile.setUserId(userId);
            creatorProfileService.updateCreatorProfile(prevProfile, creatorProfile);
            return true;
        }
        return false;
    }

    @GetMapping("/creator")
    public CreatorProfile getCreatorProfileByLink(@RequestParam String pageLink){
        Optional<CreatorProfile> content = creatorProfileService.getCreatorPageByPageLink(pageLink);
        return content.isEmpty() ? null : content.get();
    }


    @GetMapping("/all-creators")
    public List<CreatorProfile> getAllCreatorProfiles(){
        return creatorProfileService.getAll();
    }

    @GetMapping("/creators")
    public List<CreatorProfile> getAllCreatorsByName(@RequestParam String name){
        List<CreatorProfile> creatorProfile = creatorProfileService.get(name);
        return creatorProfile;
    }

    @GetMapping("/creators/category")
    public List<CreatorProfile> getCreatorsByCategory(@RequestParam Category category){
        return creatorProfileService.getContentsByCategory(category);
    }

    private boolean isCreatorAvailableForCreation(Long userId, String pageLink){
       return userId != null
               && userService.getUser(userId).isPresent()
               && creatorProfileService.isPageLinkUnique(pageLink)
               && creatorProfileService.get(userId).isEmpty();
    }
}
