package com.example.demo.controller;

import com.example.demo.exception.UserStatusException;
import com.example.demo.model.user.UserEntity;
import com.example.demo.security.jwt.JwtUtil;
import com.example.demo.service.TmpUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;
    private final TmpUser tmpUser;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, TmpUser tmpUser, JwtUtil jwtUtil) {
        this.userService = userService;
        this.tmpUser = tmpUser;
        this.jwtUtil = jwtUtil;
    }

    @CrossOrigin
    @PostMapping("/user")
    public Map<String, String> add(@RequestBody UserEntity userEntity, HttpSession session){
        if (userService.isEmailAvailable(userEntity.getEmail())){
            Map<String, String> result = new HashMap<>();
            userService.add(userEntity);
            tmpUser.setUser(userEntity.getId());
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
    public ResponseEntity<Map<String, String>> login(@RequestBody UserEntity userEntity, HttpServletResponse response){
        Optional<UserEntity> userOptional = userService.getUserByEmail(userEntity.getEmail());
        if (userOptional.isPresent() && userOptional.get().isValidPassword(userEntity.getPassword())){
            Map<String, String> result = new HashMap<>();
            UserDetails regUserEntity = userOptional.get();
            String token = jwtUtil.generateToken(regUserEntity);
            result.put("result", "ok");
            result.put("token", token);
//            tmpUser.setUser(regUserEntity.getId());
            return ResponseEntity.ok().body(result);
        }
       throw new UserStatusException("Wrong email or password!");
    }

    @CrossOrigin
    @GetMapping("/logmeout")
    public Map<String, String> logout(HttpServletRequest request, HttpServletResponse response){
        System.out.println("Tomi mindig csinos és egy kis cica");
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       if(authentication != null){
           new SecurityContextLogoutHandler().logout(request, response, authentication);
       }
       Map<String, String> resp = new HashMap<>();
       resp.put("result", "ok");
       return resp;
    }

    @GetMapping("/user/profile")
    public UserEntity getUserProfile(HttpSession session){
        Long id = (Long) session.getAttribute("userId");
        Optional<UserEntity> userOptional;
        if (id != null && (userOptional = userService.getUser(id)).isPresent()){
            return userOptional.get();
        }
        return null;
    }

    @PutMapping("/user/update")
    public void updateProfile(@RequestBody UserEntity userEntity, HttpSession session){
        if(tmpUser.getUser() != null){
            Long id = (Long) session.getAttribute("userId");
            UserEntity prevUserEntity = userService.getUser(id).get();
            userService.updateUser(prevUserEntity, userEntity);
        }
        throw new UserStatusException("You have to login to update your profile!");
    }

    //TODO: hide the logic in the service
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/user/email")
    public ResponseEntity<Map<String, String>> updateEmail(@RequestBody Map<String, String> myMail){
        String nextEmail = myMail.get("email");
        Long id = tmpUser.getUser();
        System.out.println(id);
        if (id == null) throw new UserStatusException("You need to log in to proceed!");
        Optional<UserEntity> userOptional = userService.getUser(id);
        if (userOptional.isEmpty()) throw new UserStatusException("User does not exist");
        UserEntity userEntity = userOptional.get();
        userService.updateEmail(userEntity, nextEmail);
        String newMail = userService.getUser(id).get().getEmail();
        Map<String, String> result = new HashMap<>();
        result.put("result", "ok");
        result.put("email", newMail);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/creator-profile-set")
    public ResponseEntity<Map<String, String>> isUserContentSet(Authentication authentication){
        Long userId = userService.getUserByEmail(authentication.getName()).get().getId();
        Optional<UserEntity> userOption = userService.getUser(userId);
        Map<String, String> result = new HashMap<>();
        boolean contentStatus =  userOption.map(UserEntity::isCreatorProfileAvailable).orElse(false);
        if (contentStatus){
            result.put("result", "ok");
        } else {
            result.put("result", "error");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ExceptionHandler(UserStatusException.class)
    public ResponseEntity<Map<String, String>> handleUnavailableEmail(RuntimeException exception){
        Map<String, String> result = new HashMap<>();
        result.put("result", "error");
        result.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
