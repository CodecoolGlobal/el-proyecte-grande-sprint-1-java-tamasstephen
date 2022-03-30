package com.example.demo.configuration;

import com.example.demo.model.user.CreatorProfile;
import com.example.demo.model.user.User;
import com.example.demo.service.CreatorProfileService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InitData {

    private final UserService userService;
    private final CreatorProfileService creatorProfileService;
    private static final List<String> USER_EMAILS = Arrays.asList("placeholder@example.com", "another@example.com", "andanother@example.com");
    private static final List<String> CAUSES = Arrays.asList("Find a ball for children", "Cook food for Máté", "Find a pen in the mess");
    private static final List<String> IMAGES = Arrays.asList("new-charity-card.jpg", "new-charity-old.jpg", "new-playground.jpg");

    public InitData(UserService userService, CreatorProfileService creatorProfileService) {
        this.userService = userService;
        this.creatorProfileService = creatorProfileService;
    }

    public void initUsers(){
        int count = 0;
        for (String email: USER_EMAILS){
            User user =User.builder().email(email).password("placeholder").build();
            CreatorProfile cause = CreatorProfile.builder()
                    .causeName(CAUSES.get(count))
                    .description("placeholder description")
                    .pageLink(String.format("%d link", count))
                    .profileImage(String.format("/home/tamas/Codecool/advanced-spring/el-proyecte-grande-sprint-1-java-tamasstephen/resources/images/Placeholder/%s", IMAGES.get(count)))
                    .build();
            user.setContent(cause);
            userService.add(user);
            cause.setUserId(user.getId());
            creatorProfileService.add(cause);
            count ++;
        }
    }




}
