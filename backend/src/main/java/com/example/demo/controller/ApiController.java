package com.example.demo.controller;

import com.example.demo.model.user.Content;
import com.example.demo.model.user.User;
import com.example.demo.service.ContentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public boolean add(@RequestBody Content content, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null || userService.getUser(userId).isEmpty()){
            return false;
        } else {
            User user = userService.getUser(userId).get();
            content.setUserId(userId);
            user.setContent(content);
            contentService.add(content);
            return true;
        }
    }

    @GetMapping("/all-creators")
    public List<Content> getAll(){
        return contentService.getAll();
    }
}
