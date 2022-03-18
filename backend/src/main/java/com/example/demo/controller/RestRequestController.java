package com.example.demo.controller;

import com.example.demo.model.tip.Tip;
import com.example.demo.model.user.Category;
import com.example.demo.model.user.CreatorProfile;
import com.example.demo.model.user.User;
import com.example.demo.service.CreatorProfileService;
import com.example.demo.service.TipService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
public class RestRequestController {

    // TODO: replace if else statements with try catch and return helpful return messages

    private final UserService userService;
    private final CreatorProfileService creatorProfileService;
    private final TipService tipService;

    @Autowired
    public RestRequestController(UserService userService, CreatorProfileService creatorProfileService, TipService tipService) {
        this.userService = userService;
        this.creatorProfileService = creatorProfileService;
        this.tipService = tipService;
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

    @PutMapping("/creator-profile")
    public boolean changeContent(@RequestBody CreatorProfile creatorProfile, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        Optional<CreatorProfile> profile;
        if (userId != null && (profile = creatorProfileService.get(userId)).isPresent()){
            CreatorProfile prevProfile = profile.get();
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

    @PostMapping("/creator/support")
    public void supportCreator(@RequestBody Tip tip){
        long userId = creatorProfileService.getCreatorPageByPageLink(tip.getPageLink())
                .get().getUserId();
        tip.setUserId(userId);
        tipService.add(tip);
    }

    @GetMapping("/creator/tips")
    public List<Tip> getTipCommentsByPageLink(String pageLink){
        return tipService.getCommentsByPageLink(pageLink);
    }

    private boolean isCreatorAvailableForCreation(Long userId, String pageLink){
       return userId != null
               && userService.getUser(userId).isPresent()
               && creatorProfileService.isPageLinkUnique(pageLink)
               && creatorProfileService.get(userId).isEmpty();
    }
}
