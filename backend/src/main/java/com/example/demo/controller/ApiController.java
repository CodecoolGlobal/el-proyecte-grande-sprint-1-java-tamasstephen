package com.example.demo.controller;

import com.example.demo.model.user.Content;
import com.example.demo.model.user.User;
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

    @PostMapping("/add-creator")
    public void add(@RequestBody User user, HttpSession session){
        long newId = userService.getAllUsers() == null ? 1 : userService.getAllUsers().size() + 1;
        user.setId(newId);
        userService.add(user);
        System.out.println(user.getEmail());
        System.out.println(userService.getAllUsers().size());
        session.setAttribute("userId", newId);

    }

    @PostMapping("/add-content")
    public boolean add(@RequestBody Content content, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null){
            return false;
        }
        Optional<User> user = userService.getUser(userId);
        if (user.isEmpty()){
            return false;
        }  else {
            content.setUserId(userId);
            user.get().setContent(content);
            return true;
        }
    }

    @GetMapping("/all-creators")
    public List<User> getAll(){
        return userService.getAllUsers();
    }
}
