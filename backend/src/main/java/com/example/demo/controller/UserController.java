package com.example.demo.controller;

import com.example.demo.exception.UserStatusException;
import com.example.demo.model.user.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/user")
    public Map<String, String> add(@RequestBody User user, HttpSession session){
        if (userService.isEmailAvailable(user.getEmail())){
            Map<String, String> result = new HashMap<>();
            userService.add(user);
            session.setAttribute("userId", user.getId());
            result.put("result", "ok");
            return result;
        }
        throw new UserStatusException("The provided email is already taken.");
    }

    @DeleteMapping("/user")
    public void deleteUser(HttpSession session){
        Long id = (Long) session.getAttribute("userId");
        if( id != null){
            userService.deleteUser(userService.getUser(id).get());
            session.removeAttribute("userId");
        }
    }

    @CrossOrigin
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

    @PutMapping("/user/update")
    public void updateProfile(@RequestBody User user, HttpSession session){
        if(session.getAttribute("userId") != null){
            Long id = (Long) session.getAttribute("userId");
            User prevUser = userService.getUser(id).get();
            userService.updateUser(prevUser, user);
        }
        throw new UserStatusException("You have to login to update your profile!");
    }

    @CrossOrigin
    @GetMapping("/creator-profile-set/")
    public boolean isUserContentSet(@RequestParam long id){
        Optional<User> userOption = userService.getUser(id);
        return userOption.map(User::isCreatorProfileAvailable).orElse(false);
    }

    @ExceptionHandler(UserStatusException.class)
    public ResponseEntity<Map<String, String>> handleUnavailableEmail(RuntimeException exception){
        Map<String, String> result = new HashMap<>();
        result.put("result", "error");
        result.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
