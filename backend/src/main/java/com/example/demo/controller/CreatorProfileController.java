package com.example.demo.controller;

import com.example.demo.configuration.InitData;
import com.example.demo.exception.UserStatusException;
import com.example.demo.model.responsemodel.ProfileModel;
import com.example.demo.model.tip.Tip;
import com.example.demo.model.user.Category;
import com.example.demo.model.user.CreatorProfile;
import com.example.demo.model.user.User;
import com.example.demo.service.CreatorProfileService;
import com.example.demo.service.TipService;
import com.example.demo.service.TmpUser;
import com.example.demo.service.UserService;
import com.example.demo.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@RestController
public class CreatorProfileController {

    private final UserService userService;
    private final CreatorProfileService creatorProfileService;
    private final TipService tipService;
    private final FileHandler fileHandler;
    private final TmpUser tmpUser;

    @Autowired
    public CreatorProfileController(UserService userService,
                                    CreatorProfileService creatorProfileService,
                                    TipService tipService,
                                    FileHandler fileHandler,
                                    TmpUser tmpUser) {
        this.userService = userService;
        this.creatorProfileService = creatorProfileService;
        this.tipService = tipService;
        this.fileHandler = fileHandler;
        this.tmpUser = tmpUser;
        new InitData(userService, creatorProfileService).initUsers();
    }

//TODO: remove content url from profile class -> we are going to collect for caouses
    @CrossOrigin
    @PostMapping("/creator-profile")
    public Map<String, String> uploadImage(
            @RequestPart("file") MultipartFile file,
            @RequestPart("name") String name,
            @RequestPart("description") String description,
            @RequestPart("pageLink") String pageLink,
            HttpSession session){

        Long userId = tmpUser.getUser();
        System.out.println(userId);
        Map<String, String> result = new HashMap<>();
        if (userId == null){
            throw new UserStatusException("You have to log in to create a cuase");
        }
        Optional<User> userOptional = userService.getUser(userId);
        if (userOptional.isPresent()){
            CreatorProfile profile = CreatorProfile.builder()
                    .causeName(name)
                    .description(description)
                    .pageLink(pageLink)
                    .userId(userId)
                    .build();
            fileHandler.createDirectory(name);
            Optional<String> filePath = fileHandler.saveFile(file, name);
            if (filePath.isPresent()){
                profile.setProfileImage(filePath.get());
                creatorProfileService.add(profile);
                result.put("result", "ok");
                userOptional.get().setContent(profile);
                return result;
            } else {
                throw new UserStatusException("The provided file could not be saved!");
            }
        }
        throw new UserStatusException("The provided file could not be saved!");
    }

    @CrossOrigin
    @PutMapping("/creator-profile")
    public Map<String, String> updateCreatorProfile(@RequestBody CreatorProfile creatorProfile, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        Optional<CreatorProfile> profile;
        Map<String, String> result = new HashMap<>();
        if (userId != null && (profile = creatorProfileService.get(userId)).isPresent()){
            CreatorProfile prevProfile = profile.get();
            creatorProfileService.updateCreatorProfile(prevProfile, creatorProfile);
            result.put("result", "ok");
            return result;
        }
        throw new UserStatusException("You have to log in to change those details!");
    }

    @CrossOrigin
    @GetMapping("/creator")
    public CreatorProfile getCreatorProfileByLink(@RequestParam String pageLink){
        Optional<CreatorProfile> content = creatorProfileService.getCreatorPageByPageLink(pageLink);
        return content.isEmpty() ? null : content.get();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user-profile")
    public ResponseEntity<ProfileModel> getProfileData(){
        Long userId = tmpUser.getUser();
        if (userId == null){
            throw new UserStatusException("Please log in to check your profile");
        }
        User user = userService.getUser(userId).get();
        User userToReturn = User.builder().email(user.getEmail()).build();
        CreatorProfile profile = creatorProfileService.get(userId).get();
        ProfileModel model = ProfileModel.builder().user(userToReturn).profile(profile).build();
        return ResponseEntity.status(HttpStatus.OK).body(model);
    }


    @CrossOrigin
    @GetMapping(value = "/creator/{pageLink}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> image(@PathVariable("pageLink") String pageLink) throws IOException {
        Optional<CreatorProfile> creatorOption = creatorProfileService.getCreatorPageByPageLink(pageLink);
        if (creatorOption.isEmpty()){
            throw new UserStatusException("File not found");
        }
        String image = creatorOption.get().getProfileImage() ;
        final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(image)));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(inputStream.contentLength())
                .body(inputStream);
    }


    @CrossOrigin
    @GetMapping("/all-creators")
    public List<CreatorProfile> getAllCreatorProfiles(){
        return creatorProfileService.getAll();
    }

    @CrossOrigin
    @GetMapping("/highlighted")
    public Set<CreatorProfile> getHighlightedProfiles(){
       Set<CreatorProfile> profileSet = new HashSet<>();
       for (CreatorProfile profile: creatorProfileService.getAll()){
           profileSet.add(profile);
           if(profileSet.size() > 2){
             return profileSet;
           }
       }
       return profileSet;
    }

    @CrossOrigin
    @GetMapping("/creators")
    public List<CreatorProfile> getAllCreatorsByName(@RequestParam String name){
        List<CreatorProfile> creatorProfile = creatorProfileService.get(name);
        return creatorProfile;
    }

    @CrossOrigin
    @GetMapping("/creators/category")
    public List<CreatorProfile> getCreatorsByCategory(@RequestParam Category category){
        return creatorProfileService.getContentsByCategory(category);
    }

    @CrossOrigin
    @PostMapping("/creator/support")
    public void supportCause(@RequestBody Tip tip){
        long userId = creatorProfileService.getCreatorPageByPageLink(tip.getPageLink())
                .get().getUserId();
        tip.setUserId(userId);
        tipService.add(tip);
    }

    @CrossOrigin
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


    @ExceptionHandler(UserStatusException.class)
    private ResponseEntity<Map<String, String>> handleOwnerNotFound(RuntimeException exception, WebRequest request){
        Map<String, String> result = new HashMap<>();
        result.put("result", "error");
        result.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }


}
